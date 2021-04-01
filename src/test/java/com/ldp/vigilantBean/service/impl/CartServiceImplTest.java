package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.repository.CartRepository;
import com.ldp.vigilantBean.repository.ProductRetrievalRepository;
import com.ldp.vigilantBean.security.AppUserDetails;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.service.CartService;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceImplTest {

    private CartService cartService;

    private CartRepository cartRepository;
    private AppUserRetrievalService appUserRetrievalService;
    private ProductRetrievalRepository productRetrievalRepository;

    @BeforeEach
    public void setUp() {

        cartRepository = mock(CartRepository.class);
        appUserRetrievalService = mock(AppUserRetrievalService.class);
        productRetrievalRepository = mock(ProductRetrievalRepository.class);

        cartService = new CartServiceImpl(cartRepository, appUserRetrievalService, productRetrievalRepository);
    }

    @Test
    public void shouldReturnCart() {

        AppUserDetails mockAppUserDetails = mock(AppUserDetails.class);
        AppUser mockAppUser = mock(AppUser.class);
        Cart mockCart = mock(Cart.class);

        when(appUserRetrievalService.getAppUserDetailsOutOfContext())
                .thenReturn(mockAppUserDetails);
        when(mockAppUserDetails.getAppUser())
                .thenReturn(mockAppUser);
        when(mockAppUser.getCart())
                .thenReturn(mockCart);

        Optional<Cart> optCart = cartService.getCart();

        assertTrue(optCart.isPresent());
        verify(appUserRetrievalService).getAppUserDetailsOutOfContext();
    }

    @Test
    public void shouldReturnEmptyOptional() {

        AppUserDetails mockAppUserDetails = mock(AppUserDetails.class);

        when(appUserRetrievalService.getAppUserDetailsOutOfContext())
                .thenThrow(AuthenticationCredentialsNotFoundException.class);


        Optional<Cart> optCart = cartService.getCart();

        assertFalse(optCart.isPresent());
        verify(appUserRetrievalService).getAppUserDetailsOutOfContext();
    }





}
