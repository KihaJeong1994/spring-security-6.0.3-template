package com.example.springsecuritytemplate.domain.user.dto;

import java.util.List;

public record SaveAuthorityRequest(
        String name,
        List<Long> scopes
) {
}

