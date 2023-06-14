package com.example.springsecuritytemplate.domain.user.dto;

public record LoginDto(
        String userId,
        String password
) {
}
