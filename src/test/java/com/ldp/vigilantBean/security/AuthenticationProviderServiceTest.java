package com.ldp.vigilantBean.security;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationProviderServiceTest {

    private AuthenticationProviderService authenticationProviderService;

    private PasswordEncoder mockPasswordEncoder =
            NoOpPasswordEncoder.getInstance();

    private UserDetails mockUser = User.withUsername("user")
                                       .password("validPassword")
                                       .authorities("READ")
                                       .build();

    private UserDetailsService mockUserDetailsService;

    private Authentication mockAuthentication;

    @BeforeEach
    public void setUp() {

        this.mockUserDetailsService =
                Mockito.mock(UserDetailsService.class);
        Mockito.when(mockUserDetailsService.loadUserByUsername("user"))
               .thenReturn(mockUser);

        this.mockAuthentication =
                Mockito.mock(Authentication.class);
        Mockito.when(mockAuthentication.getName())
               .thenReturn("user");

        authenticationProviderService =
                new AuthenticationProviderService(
                        mockUserDetailsService,
                        mockPasswordEncoder
                );
    }

    @Test
    @DisplayName("Should throw BadCredentials exceptions because of the wrong password")
    public void shouldInvalidateWrongPassword() {

        Mockito.when(mockAuthentication.getCredentials())
               .thenReturn("wrongPassword");

        Assertions.assertThrows(BadCredentialsException.class, () -> {
            authenticationProviderService.authenticate(mockAuthentication);
        });
    }

    @Test
    @DisplayName("Should validate user with a valid password")
    public void shouldValidateUserWithAValidPassword() {

        Mockito.when(mockAuthentication.getCredentials())
               .thenReturn("validPassword");

        Assertions.assertTrue(
                authenticationProviderService.authenticate(mockAuthentication)
                                             .isAuthenticated()
        );
    }

    @Test
    @DisplayName("Should throw Locked Exception")
    public void shouldThrowLockedException() {

        mockUser = User.withUsername("username")
                       .password("validPassword")
                       .authorities("READ")
                       .accountLocked(true)
                       .build();

        Mockito.when(mockUserDetailsService.loadUserByUsername("user"))
               .thenReturn(mockUser);

        Mockito.when(mockAuthentication.getCredentials())
               .thenReturn("validPassword");

        Assertions.assertThrows(LockedException.class, () -> {
           authenticationProviderService.authenticate(mockAuthentication);
        });
    }

    @Test
    @DisplayName("Should throw Disabled Exception")
    public void shouldThrowDisabledException() {

        mockUser = User.withUsername("username")
                .password("validPassword")
                .authorities("READ")
                .disabled(true)
                .build();

        Mockito.when(mockUserDetailsService.loadUserByUsername("user"))
                .thenReturn(mockUser);

        Mockito.when(mockAuthentication.getCredentials())
                .thenReturn("validPassword");

        Assertions.assertThrows(DisabledException.class, () -> {
            authenticationProviderService.authenticate(mockAuthentication);
        });
    }

}
