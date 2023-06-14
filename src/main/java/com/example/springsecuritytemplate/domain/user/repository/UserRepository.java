package com.example.springsecuritytemplate.domain.user.repository;

import com.example.springsecuritytemplate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(String userId);
    // TODO : add method that gets user data with authority by join
}
