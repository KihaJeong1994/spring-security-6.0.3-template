## 1. Basic Authentication

[Basic Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/basic)

## 2. JWT Authentication

### `POST` to `/user/login` with form to get jwt token
```
{
    "userId": user id,
    "password" : password
}
```

SecurityFilterChain : `WebSecurityConfig.postLoginFilterChain`

Filter : `PostLoginAuthenticationFilter` that overrides `BasicAuthenticationFilter`

AuthenticationProvider : `DaoAuthenticationProvider`

UserDetailsService : `CustomUserDetailsService`

Authentication : `UsernamePasswordAuthenticationToken`

### `GET` to `/hello` with header with bearer token from `/user/login`

SecurityFilterChain : `WebSecurityConfig.filterChain`

Filter : `BearerTokenAuthenticationFilter`

AuthenticationProvider : `JwtAuthenticationProvider`

Authentication : `BearerTokenAuthentication`

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
