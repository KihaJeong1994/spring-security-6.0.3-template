## 1. Basic Authentication

- By default, Spring Security's HTTP Basic Authentication support is enabled
- However, as soon as any servlet based configuration is provided, HTTP Basic must be explicitly provided

```
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize-> authorize
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }
```

`GET` to `/hello` with header
Authorization : Basic <token>

## 2. JWT Authentication
`POST` to `/user/login` with form to get jwt token
```
{
    "userId": user id,
    "password" : password
}
```

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
