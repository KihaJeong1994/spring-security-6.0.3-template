package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.AuthorityDto;
import com.example.springsecuritytemplate.domain.user.dto.SaveAuthorityRequest;

public interface AuthorityService {
    AuthorityDto saveAuthority(SaveAuthorityRequest saveAuthorityRequest);

    AuthorityDto getAuthority(Long id);
}
