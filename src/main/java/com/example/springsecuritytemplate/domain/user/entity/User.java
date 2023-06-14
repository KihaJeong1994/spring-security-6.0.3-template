package com.example.springsecuritytemplate.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Builder
    public User(Integer id, String userId, String password, String phone) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
    }
}
