package com.example.springsecuritytemplate.domain.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello Get", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> helloPost(){
        return new ResponseEntity<>("Hello Post", HttpStatus.OK);
    }
}
