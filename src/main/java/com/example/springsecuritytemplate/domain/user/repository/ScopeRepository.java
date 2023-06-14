package com.example.springsecuritytemplate.domain.user.repository;

import com.example.springsecuritytemplate.domain.user.entity.Scope;
import com.example.springsecuritytemplate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeRepository extends JpaRepository<Scope,Long> {
}
