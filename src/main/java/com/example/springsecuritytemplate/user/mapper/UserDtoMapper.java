package com.example.springsecuritytemplate.user.mapper;

import com.example.springsecuritytemplate.user.dto.UserDto;
import com.example.springsecuritytemplate.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getUserId(),
                user.getPassword(),
                user.getPhone()
        );
    }
}
