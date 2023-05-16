package com.example.springsecuritytemplate.user.dto;

public record LoginDto(
        String userId,
        String password
) {
}
