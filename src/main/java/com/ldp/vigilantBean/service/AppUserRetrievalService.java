package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.security.AppUserDetails;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Optional;

public interface AppUserRetrievalService {

    Optional<AppUser> getAppUserByEmail(String email);

    AppUserDetails getAppUserDetailsOutOfContext() throws AuthenticationCredentialsNotFoundException;
}
