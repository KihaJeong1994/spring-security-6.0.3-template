package com.example.springsecuritytemplate.domain.user.repository;

import com.example.springsecuritytemplate.domain.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
