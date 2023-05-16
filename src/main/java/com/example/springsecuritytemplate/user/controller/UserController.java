package com.example.springsecuritytemplate.user.controller;

import com.example.springsecuritytemplate.user.dto.UserDto;
import com.example.springsecuritytemplate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtEncoder jwtEncoder;

    @PostMapping("")
    public ResponseEntity register(@RequestBody UserDto userDto){
        UserDto registered = userService.register(userDto);
        return new ResponseEntity(registered,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS512).type("JWT").build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header,claims)).getTokenValue();
        return new ResponseEntity(token,HttpStatus.OK);
    }

}
