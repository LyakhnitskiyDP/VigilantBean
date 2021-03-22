package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import com.ldp.vigilantBean.security.AppUserDetails;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class AppUserRetrievalServiceImpl implements AppUserRetrievalService {

    private AppUserRetrievalRepository appUserRetrievalRepository;

    public AppUserRetrievalServiceImpl(
            @Autowired
            AppUserRetrievalRepository appUserRetrievalRepository) {

       this.appUserRetrievalRepository = appUserRetrievalRepository;
    }

    @Override
    public Optional<AppUser> getAppUserByEmail(String email) {

        return appUserRetrievalRepository.getAppUserByEmail(email);
    }


    @Override
    public AppUserDetails getAppUserDetailsOutOfContext()
            throws AuthenticationCredentialsNotFoundException {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");

        if (AppUserDetails.class.isAssignableFrom(authentication.getPrincipal().getClass()))
            return (AppUserDetails) authentication.getPrincipal();
        else {
            throw new AuthenticationCredentialsNotFoundException("Unknown principle");
        }
    }
}
