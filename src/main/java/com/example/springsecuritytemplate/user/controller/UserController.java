package com.example.springsecuritytemplate.user.controller;

import com.example.springsecuritytemplate.user.dto.UserDto;
import com.example.springsecuritytemplate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity register(@RequestBody UserDto userDto){
        UserDto registered = userService.register(userDto);
        return new ResponseEntity(registered,HttpStatus.OK);
    }

}
