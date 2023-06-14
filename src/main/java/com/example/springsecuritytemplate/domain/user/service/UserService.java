package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);

    UserDto getUser(String userId);
}
