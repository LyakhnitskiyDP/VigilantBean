package com.ldp.vigilantBean.security;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private AppUserRetrievalRepository appUserRetrievalRepository;

    public AppUserDetailsService(
            @Autowired
            AppUserRetrievalRepository appUserRetrievalRepository) {

        this.appUserRetrievalRepository = appUserRetrievalRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<AppUser> optUser =
                appUserRetrievalRepository.getUserByUsername(username);

        UserDetails userDetails =
                new AppUserDetails(optUser.orElseThrow(
                        () -> new UsernameNotFoundException(username + " cannot be found") )
                );

        return userDetails;
    }
}
