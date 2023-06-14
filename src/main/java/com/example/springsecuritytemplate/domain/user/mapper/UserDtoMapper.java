package com.example.springsecuritytemplate.domain.user.mapper;

import com.example.springsecuritytemplate.domain.user.dto.AuthorityDto;
import com.example.springsecuritytemplate.domain.user.dto.UserDto;
import com.example.springsecuritytemplate.domain.user.dto.UserWithAuthorityDto;
import com.example.springsecuritytemplate.domain.user.entity.User;

public class UserDtoMapper {
    public UserDto toDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getPassword(),
                user.getPhone(),
                user.getAuthority().getId()
        );
    }

    public UserWithAuthorityDto toUserWithAuthorityDto(User user) {
        return new UserWithAuthorityDto(
                user.getUserId(),
                user.getPassword(),
                user.getPhone(),
                new AuthorityDto(user.getAuthority().getName()
                        ,user.getAuthority().getScopes()
                        .stream().map(s->s.getScope().getName())
                        .toList()
                )
        );
    }
}
