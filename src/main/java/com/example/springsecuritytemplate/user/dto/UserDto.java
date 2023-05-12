package com.example.springsecuritytemplate.user.dto;

public record UserDto(
        String userId,
        String password,
        String phone
) {
}
