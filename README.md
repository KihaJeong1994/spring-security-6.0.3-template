## 1. Basic Authentication

[Basic Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/basic)

## 2. JWT Authentication

### `POST` to `/user/login` with form to get jwt token that has scopes
authorityId

1 : ADMIN : all

2 : WRITE : POST /hello, POST bye

3 : READ : GET /hello, GET /bye
```
{
    "userId": user id,
    "password" : password,
    "phone": "01011112222",
    "authorityId":1
}
```

SecurityFilterChain : `WebSecurityConfig.postLoginFilterChain`

Filter : `PostLoginAuthenticationFilter` that overrides `BasicAuthenticationFilter`

AuthenticationProvider : `DaoAuthenticationProvider`

UserDetailsService : `CustomUserDetailsService`

Authentication : `UsernamePasswordAuthenticationToken`

### `POST` to `/hello` with header with bearer token from `/user/login`

SecurityFilterChain : `WebSecurityConfig.filterChain`

Filter : `BearerTokenAuthenticationFilter`

AuthenticationProvider : `JwtAuthenticationProvider`

Authentication : `BearerTokenAuthentication`

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
