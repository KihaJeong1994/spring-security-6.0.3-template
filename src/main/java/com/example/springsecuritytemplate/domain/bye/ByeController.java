package com.example.springsecuritytemplate.domain.bye;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bye")
public class ByeController {

    @GetMapping("")
    public ResponseEntity<String> bye(){
        return new ResponseEntity<>("Bye Get", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> byePost(){
        return new ResponseEntity<>("Bye Post", HttpStatus.OK);
    }
}
