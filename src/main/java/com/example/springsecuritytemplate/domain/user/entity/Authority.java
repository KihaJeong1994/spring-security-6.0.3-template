package com.example.springsecuritytemplate.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @OneToMany(
            mappedBy = "authority",
            cascade = CascadeType.ALL,
            orphanRemoval = true // rows in join table is not going to be reused as the relationship is removed
    )
    private List<AuthorityScope> scopes;

    @Builder
    public Authority(String name,List<AuthorityScope> scopes) {
        this.name = name;
        this.scopes = scopes!=null? scopes: new ArrayList<>();
    }

    public void addScope(Scope scope){
        AuthorityScope projectScope = new AuthorityScope(this, scope);
        this.scopes.add(projectScope);
        scope.getAuthorities().add(projectScope);
    }

    public void removeScope(Scope scope){
        AuthorityScope projectScope = new AuthorityScope(this, scope);
        this.scopes.remove(projectScope);
        scope.getAuthorities().remove(projectScope);
        projectScope.setAuthority(null);
        projectScope.setScope(null);
    }
}
