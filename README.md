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
    
[JWT Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/jwt)

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
