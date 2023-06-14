package com.example.springsecuritytemplate.domain.user.dto;

import java.util.List;

public record AuthorityDto(
        String name,
        List<String> scopes
) {
}

