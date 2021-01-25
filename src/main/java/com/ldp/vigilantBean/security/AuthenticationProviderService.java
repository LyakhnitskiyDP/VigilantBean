package com.ldp.vigilantBean.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    public AuthenticationProviderService(
            @Autowired
            UserDetailsService userDetailsService,
            @Autowired
            PasswordEncoder passwordEncoder) {

       this.userDetailsService = userDetailsService;
       this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        return checkPassword(user, password);
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }

    private Authentication checkPassword(UserDetails user,
                                         String password) {

       if (passwordEncoder.matches(password, user.getPassword())) {

           return new UsernamePasswordAuthenticationToken(
                   user.getUsername(),
                   user.getPassword(),
                   user.getAuthorities()
           );
       } else
           throw new BadCredentialsException("Exception while checking password");

    }
}
