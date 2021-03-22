package com.ldp.vigilantBean.security;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger log =
            LogManager.getLogger(AppUserDetailsService.class.getName());

    private AppUserRetrievalRepository appUserRetrievalRepository;

    public AppUserDetailsService(
            @Autowired
            AppUserRetrievalRepository appUserRetrievalRepository) {

        this.appUserRetrievalRepository = appUserRetrievalRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        log.info("Trying to load user by name: " + email);

        Optional<AppUser> optUser =
                appUserRetrievalRepository.getAppUserByEmail(email);

        UserDetails userDetails =
                new AppUserDetails(optUser.orElseThrow(
                        () -> new UsernameNotFoundException(email + " cannot be found") )
                );

        return userDetails;
    }
}
