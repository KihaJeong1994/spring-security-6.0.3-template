package com.example.springsecuritytemplate.domain.user.mapper;

import com.example.springsecuritytemplate.domain.user.dto.UserDto;
import com.example.springsecuritytemplate.domain.user.entity.User;
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
