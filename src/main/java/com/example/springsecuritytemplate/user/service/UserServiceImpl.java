package com.example.springsecuritytemplate.user.service;

import com.example.springsecuritytemplate.user.dto.UserDto;
import com.example.springsecuritytemplate.user.entity.User;
import com.example.springsecuritytemplate.user.mapper.UserDtoMapper;
import com.example.springsecuritytemplate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


}
