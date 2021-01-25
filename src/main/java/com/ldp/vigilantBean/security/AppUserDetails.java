package com.ldp.vigilantBean.security;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.appUser.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class AppUserDetails implements UserDetails {

    private AppUser appUser;

    public AppUserDetails(AppUser appUser) {
       this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return appUser.getRoles().stream()
                                       .map(Role::getName)
                                       .map(SimpleGrantedAuthority::new)
                                       .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.isEnabled();
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
