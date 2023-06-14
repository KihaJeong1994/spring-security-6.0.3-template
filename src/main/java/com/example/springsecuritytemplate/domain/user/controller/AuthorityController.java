package com.example.springsecuritytemplate.domain.user.controller;

import com.example.springsecuritytemplate.domain.user.dto.AuthorityDto;
import com.example.springsecuritytemplate.domain.user.dto.SaveAuthorityRequest;
import com.example.springsecuritytemplate.domain.user.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authority")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;


    @PostMapping("")
    public ResponseEntity<?> saveAuthority(@RequestBody SaveAuthorityRequest authorityRequest){
        AuthorityDto saveAuthorityDto = authorityService.saveAuthority(authorityRequest);
        return new ResponseEntity<>(saveAuthorityDto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthority(@PathVariable Long id){
        AuthorityDto saveAuthorityDto = authorityService.getAuthority(id);
        return new ResponseEntity<>(saveAuthorityDto,HttpStatus.CREATED);
    }

}
