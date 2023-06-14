package com.example.springsecuritytemplate.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @OneToMany(
            mappedBy = "scope",
            cascade = CascadeType.ALL,
            orphanRemoval = true // rows in join table is not going to be reused as the relationship is removed
    )
    @Setter
    private List<AuthorityScope> authorities;

    @Builder
    public Scope(String name,List<AuthorityScope> authorities) {
        this.name = name;
        this.authorities = authorities!=null?authorities:new ArrayList<>();
    }


}
