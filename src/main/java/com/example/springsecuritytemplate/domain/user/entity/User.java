package com.example.springsecuritytemplate.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String phone;

    @OneToOne
    @Setter
    private Authority authority;

    @Builder
    public User(String userId, String password, String phone,Authority authority) {
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.authority = authority;
    }

}
