package com.example.springsecuritytemplate.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWhenUnAuthenticatedThenUnauthorized() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser // almost same as code below
    void helloWhenAuthenticatedThenOk() throws Exception {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        Authentication authentication =
//                new TestingAuthenticationToken("username", "password", "ROLE_USER");
//        context.setAuthentication(authentication);
//
//        SecurityContextHolder.setContext(context);
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("username: "+ authentication.getName());
        System.out.println("principal: "+ authentication.getPrincipal());
        System.out.println("credentials: "+ authentication.getCredentials());
        System.out.println("authorities: "+ authentication.getAuthorities());
        System.out.println("isAuthenticated: "+ authentication.isAuthenticated());
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

}