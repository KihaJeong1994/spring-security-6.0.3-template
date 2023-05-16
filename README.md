# Spring Security Template

## 0. Architecture

### 1) Authentication Architecture
![](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/securitycontextholder.png)

**Authentication vs UserDetails**

Authentication ⊃ UserDetails

Authentication consists of
- principal : Identifies the user. Often an instance of UserDetails
- credentials : Often a password
- authorities : permissions the user is granted

**AuthenticationProvider**

- the one who actually perform authentication
- DaoAuthenticationProvider : username/password
- JwtAuthenticationProvider : authenticating a JWT token

**AbstractAuthenticationProcessingFilter**
- a base filter that authenticates a user's credential
- creates ***Authentication*** instance based on subclass of this filter
- UsernamePasswordAuthenticationFilter -> UsernamePasswordAuthenticationToken : username/password auth by form
- BasicAuthenticationFilter -> UsernamePasswordAuthenticationToken :username/password auth by Basic header 

![](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/abstractauthenticationprocessingfilter.png)

**UserDetailsService**

- used by DaoAuthenticationProvider
- defines the way to retrieve UserDetails with a username, password, etc (ex. from DB or memory, etc)

### Main Objects for implementation

SecurityFilterChain : which filters to use for matched request by Spring Security

Filter : extract `Authentication` info from request and perform authentication by `AuthenticationProvider`

AuthenticationProvider : one that actually perform authentication. used by Filter.authenticationManager

UserDetailsService : used by DaoAuthenticationProvider to get stored info from DB, memory, etc

Authentication : object that contains auth info

`POST` to `/signup` endpoint to save user info in database

    {
        "userId" : id,
        "password": password,
        "phone": phone number
    }

## 1. Basic Authentication

[Basic Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/basic)


## 2. JWT Authentication

[JWT Authentication](https://github.com/KihaJeong1994/spring-security-6.0.3-template/tree/jwt)

## 3. OAuth(TODO)

## 4. Spring Security Test

### 1) @WithMockUser

- annotation used to pass the spring security
