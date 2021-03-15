package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.order.CartItem;
import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.exception.ProductNotFoundException;
import com.ldp.vigilantBean.repository.ProductRetrievalRepository;
import com.ldp.vigilantBean.security.AppUserDetails;
import com.ldp.vigilantBean.service.AppUserAlterService;
import com.ldp.vigilantBean.service.CartService;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private ProductRetrievalRepository productRetrievalRepository;

    private AppUserAlterService appUserAlterService;

    public CartServiceImpl(
            @Autowired
            AppUserAlterService appUserAlterService,
            @Autowired
            ProductRetrievalRepository productRetrievalRepository) {

        this.productRetrievalRepository = productRetrievalRepository;
        this.appUserAlterService = appUserAlterService;
    }

    @Override
    public boolean addCartItem(CartItemDTO cartItemDTO)
            throws AuthenticationCredentialsNotFoundException {

        AppUserDetails appUserDetails = getAppUserDetails();

        AppUser appUser = appUserDetails.getAppUser();
        CartItem cartItem = extractCartItem(cartItemDTO);

        System.out.println(appUser.toString());
        appUser.getCart().addCartItem(cartItem);

        appUserAlterService.updateUser(appUser);

        return false;
    }

    private AppUserDetails getAppUserDetails() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");

        if (authentication.getPrincipal() instanceof AppUserDetails)
            return (AppUserDetails) authentication.getPrincipal();
        else
            throw new AuthenticationCredentialsNotFoundException("Unknown principle");
    }

    private CartItem extractCartItem(CartItemDTO cartItemDTO) {

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDTO.getQuantity());

        Product product = productRetrievalRepository.getProductById(
                cartItemDTO.getProductId())
                           .orElseThrow(
                                   () -> new ProductNotFoundException(cartItemDTO.getProductId())
                           );

        cartItem.setProduct(product);

        return  cartItem;
    }

}
