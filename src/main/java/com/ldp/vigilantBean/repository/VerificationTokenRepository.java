package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.registration.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {

    Optional<VerificationToken> create(VerificationToken token);

}
