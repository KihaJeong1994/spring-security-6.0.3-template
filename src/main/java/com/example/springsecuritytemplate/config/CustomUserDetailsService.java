package com.example.springsecuritytemplate.config;

import com.example.springsecuritytemplate.domain.user.dto.UserWithAuthorityDto;
import com.example.springsecuritytemplate.domain.user.service.UserService;
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

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 1. retrieve data from user service
        UserWithAuthorityDto userDto = userService.getUser(userId);
        // 2. wrap it with UserDetails
        return new CustomUserDetails(userDto);
    }

    final class CustomUserDetails implements UserDetails{

        private UserWithAuthorityDto userDto;

        private static final List<GrantedAuthority> ROLE_USER = Collections.unmodifiableList(
                AuthorityUtils.createAuthorityList("ROLE_USER")
        ) ;

        CustomUserDetails(UserWithAuthorityDto userDto){
            this.userDto = userDto;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.unmodifiableList(
                    AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", userDto.authorityDto().scopes()))
            ) ;
        }


        // Custom getUsername so it can be used in UserDetailsService.loadByUsername
        @Override
        public String getUsername() {
            return userDto.userId();
        }
        @Override
        public String getPassword() {
            return userDto.password();
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
