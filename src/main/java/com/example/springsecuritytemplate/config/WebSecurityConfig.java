package com.example.springsecuritytemplate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;


    @Bean
    @Order(0)
    public SecurityFilterChain postLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatchers((matchers)->matchers.requestMatchers(HttpMethod.POST, "/user/login"))
                .csrf().disable()
                .authorizeHttpRequests(authorize->authorize
                        .anyRequest().authenticated()
                )
                .addFilterAt(new PostLoginAuthenticationFilter(authenticationManager(),authenticationEntryPoint()), BasicAuthenticationFilter.class)
                ;
        return http.build();
    }

    @Bean
//    @Order(1) // no @Order defaults to last
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf)->csrf.ignoringRequestMatchers("/user")) // if we don't add this option, POST or PUT protects csrf by default -> always 401 when POST,PUT
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST,"/user").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }



//    @Bean // not recommended because ignoring does not support some default security option (ex. security header, etc) https://github.com/spring-projects/spring-security/issues/10938
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().requestMatchers("/user","/h2-console");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new BasicAuthenticationEntryPoint();
    }

}
