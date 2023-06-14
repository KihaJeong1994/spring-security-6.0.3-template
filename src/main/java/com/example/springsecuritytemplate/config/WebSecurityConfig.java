package com.example.springsecuritytemplate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {



    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    @Order(0)
    public SecurityFilterChain postLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatchers((matchers)->matchers.requestMatchers(HttpMethod.POST, "/user/login"))
                .csrf().disable()
                .authorizeHttpRequests(authorize->authorize
                        .anyRequest().authenticated()
                )
                .addFilterAt(new PostLoginAuthenticationFilter(authenticationManager(),new BasicAuthenticationEntryPoint()), BasicAuthenticationFilter.class)
                ;
        return http.build();
    }

    @Bean
//    @Order(1) // no @Order defaults to last
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf)->csrf.ignoringRequestMatchers("/user")) // if we don't add this option, POST or PUT protects csrf by default -> always 401 when POST,PUT
//                .csrf((csrf)->csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST,"/user").permitAll()
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer((oauth2ResourceServer)->oauth2ResourceServer
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // to apply authenticationEntryPoint, accessDeniedHandler, you need to apply here. below at exceptionHandling is not working
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                        .jwt(Customizer.withDefaults())
                ) // when added, BearerTokenAuthenticationFilter is activated
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling((exceptions)-> exceptions
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) // implements AuthenticationEntryPoint to custom error response when authentication failed
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()) // implements AccessDeniedHandler to custom error response when authorization failed
//                )
        ;
        return http.build();
    }



//    @Bean // not recommended because ignoring does not support some default security option (ex. security header, etc) https://github.com/spring-projects/spring-security/issues/10938
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().requestMatchers("/user","/h2-console");
//    }



    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }




}
