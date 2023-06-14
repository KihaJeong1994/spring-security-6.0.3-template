package com.example.springsecuritytemplate.domain.user.mapper;

import com.example.springsecuritytemplate.domain.user.dto.AuthorityDto;
import com.example.springsecuritytemplate.domain.user.entity.Authority;

public class AuthorityDtoMapper{


    public AuthorityDto toGetDto(Authority authority) {
        return new AuthorityDto(
                authority.getName(),
                authority.getScopes().stream().map(s->{
                    return s.getScope().getName();
                }).toList()
        );
    }
}
