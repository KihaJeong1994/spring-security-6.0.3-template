## 1. Basic Authentication

[Basic Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/basic)

## 2. JWT Authentication
`POST` to `/user/login` with form to get jwt token
```
{
    "userId": user id,
    "password" : password
}
```

`GET` to `/hello` with header with token from `/user/login`

Authorization: Bearer $TOKEN

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
