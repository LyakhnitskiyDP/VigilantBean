package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import com.ldp.vigilantBean.service.impl.AppUserRegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AppUserRegistrationServiceImplTest {

    private AppUserRegistrationService appUserRegistrationService;

    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    private AppUserAlterRepository appUserAlterRepository;

    @BeforeEach
    public void setUp() {

        /*
        appUserAlterRepository = Mockito.mock(AppUserAlterRepository.class);
        Mockito.when(appUserAlterRepository.registerUser(ArgumentMatchers.any(AppUser)))
               .thenAnswer();
         */

        appUserRegistrationService = new AppUserRegistrationServiceImpl(
                appUserAlterRepository,
                passwordEncoder
        );
    }

    private static class IdenticalUser implements Answer<Optional<AppUser>> {

        @Override
        public Optional<AppUser> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return Optional.of(invocationOnMock.getArgument(0));
        }
    }


}
