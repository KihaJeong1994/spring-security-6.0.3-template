package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.UserDto;
import com.example.springsecuritytemplate.domain.user.dto.UserWithAuthorityDto;
import com.example.springsecuritytemplate.domain.user.entity.Authority;
import com.example.springsecuritytemplate.domain.user.entity.User;
import com.example.springsecuritytemplate.domain.user.mapper.UserDtoMapper;
import com.example.springsecuritytemplate.domain.user.repository.AuthorityRepository;
import com.example.springsecuritytemplate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserDtoMapper userDtoMapper = new UserDtoMapper();
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        Authority authority = authorityRepository.findById(userDto.authorityId()).orElseThrow(()->new NoSuchElementException());
        User user = User.builder()
                .userId(userDto.userId())
                .password(passwordEncoder.encode(userDto.password()))
                .phone(userDto.phone())
                .authority(authority)
                .build();
        return userDtoMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserWithAuthorityDto getUser(String userId) {
        return userRepository.findByUserId(userId)
                .map(userDtoMapper::toUserWithAuthorityDto)
                .orElseThrow(()->new NoSuchElementException());
    }


}
