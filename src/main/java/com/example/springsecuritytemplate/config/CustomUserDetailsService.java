package com.example.springsecuritytemplate.config;

import com.example.springsecuritytemplate.domain.user.entity.User;
import com.example.springsecuritytemplate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 1. retrieve data from DB
        User user = userRepository.findByUserId(userId).orElseThrow();
        // 2. wrap it with UserDetails
        return new CustomUserDetails(user);
    }

    static final class CustomUserDetails extends User implements UserDetails{

        private static final List<GrantedAuthority> ROLE_USER = Collections.unmodifiableList(
                AuthorityUtils.createAuthorityList("ROLE_USER")
        ) ;

        CustomUserDetails(User user){
            super(user.getId(), user.getUserId(), user.getPassword(), user.getPhone());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLE_USER;
        }

        // Custom getUsername so it can be used in UserDetailsService.loadByUsername
        @Override
        public String getUsername() {
            return getUserId();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
