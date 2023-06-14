package com.example.springsecuritytemplate.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorityScope {

    @Id
    @ManyToOne
    private Authority authority;

    @Id
    @ManyToOne
    private Scope scope;

    @Builder
    public AuthorityScope(Authority authority, Scope scope) {
        this.authority = authority;
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityScope that = (AuthorityScope) o;
        return Objects.equals(authority, that.authority) && Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, scope);
    }
}
