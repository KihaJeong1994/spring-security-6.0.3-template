package com.example.springsecuritytemplate.domain.user.service;

import com.example.springsecuritytemplate.domain.user.dto.UserDto;
import com.example.springsecuritytemplate.domain.user.entity.User;
import com.example.springsecuritytemplate.domain.user.mapper.UserDtoMapper;
import com.example.springsecuritytemplate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.userId())
                .password(passwordEncoder.encode(userDto.password()))
                .phone(userDto.phone())
                .build();
        return userDtoMapper.apply(userRepository.save(user));
    }

    @Override
    public UserDto getUser(String userId) {
        return userRepository.findByUserId(userId)
                .map(userDtoMapper)
                .orElseThrow(()->new NoSuchElementException());
    }


}
