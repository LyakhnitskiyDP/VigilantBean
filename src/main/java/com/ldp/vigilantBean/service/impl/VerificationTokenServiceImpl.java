package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;
import com.ldp.vigilantBean.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@PropertySource("classpath:webappConfig.properties")
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    @Value("${verificationToken.expirationInMinutes}")
    private int tokenExpirationInMinutes;

    public VerificationTokenServiceImpl(
            @Autowired
            VerificationTokenRepository verificationTokenRepository) {

        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    public Optional<VerificationToken> create(AppUser user, String token) {

        VerificationToken verificationToken =
                new VerificationToken(new Date(), tokenExpirationInMinutes);

        verificationToken.setToken(token);
        verificationToken.setAppUser(user);


        return verificationTokenRepository.create(verificationToken);
    }

    @Override
    public Optional<VerificationToken> get(String token) {

        return verificationTokenRepository.get(token);
    }

    public void setTokenExpirationInMinutes(int tokenExpirationInMinutes) {
        if (tokenExpirationInMinutes > 1)
            this.tokenExpirationInMinutes = tokenExpirationInMinutes;
        else
            throw new IllegalArgumentException("0 or negative number number of minutes of token expiration time");
    }
}
