package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;

import java.util.Optional;

public interface VerificationTokenService {

    Optional<VerificationToken> create(AppUser user, String token);

    Optional<VerificationToken> get(String token);

}
