package com.example.springsecuritytemplate.domain.user.dto;


public record UserWithAuthorityDto(
        String userId,
        String password,
        String phone,
        AuthorityDto authorityDto
) {
}
