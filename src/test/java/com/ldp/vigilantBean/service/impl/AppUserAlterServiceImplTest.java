package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import com.ldp.vigilantBean.service.AppUserAlterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class AppUserAlterServiceImplTest {

    private AppUserAlterService appUserAlterService;

    private PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(NoOpPasswordEncoder.getInstance());

    private AppUserAlterRepository appUserAlterRepository;

    private AppUserDTO userDTO = new AppUserDTO();

    @BeforeEach
    public void setUp() {

        appUserAlterRepository = mock(AppUserAlterRepository.class);
        when(appUserAlterRepository.registerUser(any(AppUser.class)))
                                   .thenAnswer(new IdenticalUser());

        appUserAlterService = new AppUserAlterServiceImpl(
                appUserAlterRepository,
                passwordEncoder
        );

        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setEmail("email");
    }

    private static class IdenticalUser implements Answer<Optional<AppUser>> {

        @Override
        public Optional<AppUser> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return Optional.of(invocationOnMock.getArgument(0));
        }
    }

    /**
     * Created to be spied upon by Mockito.spy().
     * NoOnPasswordEncoder is final, thus no possibility to spy with Mockito.
     */
    private static class DelegatingPasswordEncoder implements PasswordEncoder {

        private PasswordEncoder passwordEncoder;

        public DelegatingPasswordEncoder(PasswordEncoder passwordEncoder) {
           this.passwordEncoder = passwordEncoder;
        }

        @Override
        public String encode(CharSequence charSequence) {
            return passwordEncoder.encode(charSequence);
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return passwordEncoder.matches(charSequence, s);
        }
    }

    @Test
    public void shouldRegisterNewUser() {

        Optional<AppUser> optAppUser = appUserAlterService.registerUser(userDTO);

        assertTrue(optAppUser.isPresent());

        AppUser savedUser = optAppUser.get();
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
        assertEquals(userDTO.getUsername(), savedUser.getUsername());

        verify(appUserAlterRepository).registerUser(any(AppUser.class));
        spy(passwordEncoder).encode(anyString());
    }


}
