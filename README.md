## 1. Basic Authentication

![](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/basicauthenticationfilter.png)

### `GET` to `/hello` with Authorization header

SecurityFilterChain : `WebSecurityConfig.filterChain`

Filter : `BasicAuthenticationFilter`

AuthenticationProvider : `DaoAuthenticationProvider`

UserDetailsService : `CustomUserDetailsService`

Authentication : `UsernamePasswordAuthenticationToken`

## 2. JWT Authentication
    
[JWT Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/jwt)

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
