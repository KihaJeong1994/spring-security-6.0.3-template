package com.example.springsecuritytemplate.user.service;

import com.example.springsecuritytemplate.user.dto.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);
}
