package com.example.springsecuritytemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Bean
//    @Order(0)
//    public SecurityFilterChain allowSignupFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatchers((matchers)->matchers.requestMatchers(HttpMethod.POST,"/user"))
//                .csrf((csrf)->csrf.ignoringRequestMatchers("/user"))
//                .authorizeHttpRequests(authorize-> authorize
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }

    @Bean
//    @Order(1) // can permit POST /user by add another filterchain like above
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf)->csrf.ignoringRequestMatchers("/user")) // if we don't add this option, POST or PUT protects csrf by default -> always 401 when POST,PUT
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST,"/user").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()) // enable BASIC authentication
        ;
        return http.build();
    }

//    @Bean // not recommended because ignoring does not support some default security option (ex. security header, etc)
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().requestMatchers("/user","/h2-console");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
