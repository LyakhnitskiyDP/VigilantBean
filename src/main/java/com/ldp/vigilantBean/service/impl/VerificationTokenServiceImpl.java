package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;
import com.ldp.vigilantBean.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(
            @Autowired
            VerificationTokenRepository verificationTokenRepository) {

        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    public Optional<VerificationToken> create(AppUser user, String token) {

        VerificationToken verificationToken =
                new VerificationToken(new Date());

        verificationToken.setToken(token);
        verificationToken.setAppUser(user);

        return verificationTokenRepository.create(verificationToken);
    }
}
