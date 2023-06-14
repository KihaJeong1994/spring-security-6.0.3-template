package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.UserDto;
import com.example.springsecuritytemplate.domain.user.dto.UserWithAuthorityDto;

public interface UserService {
    UserDto register(UserDto userDto);

    UserWithAuthorityDto getUser(String userId);
}
