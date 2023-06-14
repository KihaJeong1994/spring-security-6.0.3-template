package com.example.springsecuritytemplate.domain.user.dto;

public record UserDto(
        String userId,
        String password,
        String phone
) {
}
