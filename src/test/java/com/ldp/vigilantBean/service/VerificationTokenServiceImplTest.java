package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;
import com.ldp.vigilantBean.service.impl.VerificationTokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class VerificationTokenServiceImplTest {

    private VerificationTokenServiceImpl verificationTokenService;

    private VerificationTokenRepository verificationTokenRepository;

    private AppUser user;

    @BeforeEach
    public void setUp() {

        verificationTokenRepository = Mockito.mock(VerificationTokenRepository.class);
        Mockito.when(verificationTokenRepository.create(ArgumentMatchers.any(VerificationToken.class)))
               .thenAnswer(new IdenticalVerificationToken());

        user = new AppUser();

        verificationTokenService =
                new VerificationTokenServiceImpl(verificationTokenRepository);
        verificationTokenService.setTokenExpirationInMinutes(5);
    }

    private static class IdenticalVerificationToken implements Answer<Optional<Object>> {

        @Override
        public Optional<Object> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return Optional.of(invocationOnMock.getArgument(0));
        }
    }

    @Test
    @DisplayName("Should create a valid token")
    public void testVerificationTokenCreation() {

        String token = UUID.randomUUID().toString();

        Optional<VerificationToken> optVerificationToken =
                verificationTokenService.create(user, token);

        Assertions.assertTrue(optVerificationToken.isPresent());

        VerificationToken verificationToken = optVerificationToken.get();

        Assertions.assertEquals(token, verificationToken.getToken());
        Assertions.assertEquals(user, verificationToken.getAppUser());
        Assertions.assertEquals(5, verificationToken.getExpirationInMinutes());

        org.assertj.core.api.Assertions.assertThat(
                verificationToken.getExpiryDate()
        ).isAfter(new Date());
    }

    @Test
    @DisplayName("Should throw an IllegalArgumenException with invalid number of minutes")
    public void testSettingInvalidNumberOfMinutes() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.verificationTokenService.setTokenExpirationInMinutes(0));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.verificationTokenService.setTokenExpirationInMinutes(-42));
    }

}
