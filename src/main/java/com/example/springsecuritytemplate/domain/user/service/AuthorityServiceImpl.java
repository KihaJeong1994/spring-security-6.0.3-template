package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.AuthorityDto;
import com.example.springsecuritytemplate.domain.user.dto.SaveAuthorityRequest;
import com.example.springsecuritytemplate.domain.user.entity.Authority;
import com.example.springsecuritytemplate.domain.user.entity.Scope;
import com.example.springsecuritytemplate.domain.user.mapper.AuthorityDtoMapper;
import com.example.springsecuritytemplate.domain.user.repository.AuthorityRepository;
import com.example.springsecuritytemplate.domain.user.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService{
    private final AuthorityRepository authorityRepository;
    private final ScopeRepository scopeRepository;
    private final AuthorityDtoMapper authorityDtoMapper = new AuthorityDtoMapper();

    @Override
    public AuthorityDto saveAuthority(SaveAuthorityRequest saveAuthorityRequest) {
        Authority authority = Authority.builder()
                .name(saveAuthorityRequest.name())
                .build();
        for(Long l : saveAuthorityRequest.scopes()){
            Optional<Scope> scope = scopeRepository.findById(l);
            if(scope.isPresent()){
                authority.addScope(scope.get());
            }else {
                throw new NoSuchElementException();
            }
        }
        return authorityDtoMapper.toGetDto(authorityRepository.save(authority));
    }

    @Override
    public AuthorityDto getAuthority(Long id) {
        return authorityRepository.findById(id)
                .map(authorityDtoMapper::toGetDto)
                .orElseThrow(()->new NoSuchElementException());
    }
}
