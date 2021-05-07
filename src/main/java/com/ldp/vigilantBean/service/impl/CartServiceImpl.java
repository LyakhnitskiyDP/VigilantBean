package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.domain.order.CartItem;
import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.domain.order.Coupon;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.exception.ProductNotFoundException;
import com.ldp.vigilantBean.repository.*;
import com.ldp.vigilantBean.security.AppUserDetails;
import com.ldp.vigilantBean.service.AppUserAlterService;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.service.CartService;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import com.ldp.vigilantBean.validator.CouponValidator;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
class CartServiceImpl implements CartService {

    private ProductRetrievalRepository productRetrievalRepository;

    private AppUserRetrievalService appUserRetrievalService;

    private CartRepository cartRepository;

    private CouponRepository couponRepository;

    private CouponValidator couponValidator;

    public CartServiceImpl(
            @Autowired
            CartRepository cartRepository,
            @Autowired
            AppUserRetrievalService appUserRetrievalService,
            @Autowired
            ProductRetrievalRepository productRetrievalRepository) {

        this.productRetrievalRepository = productRetrievalRepository;
        this.cartRepository = cartRepository;
        this.appUserRetrievalService = appUserRetrievalService;
    }

    @Autowired
    public void setCouponValidator(CouponValidator couponValidator) {
        this.couponValidator = couponValidator;
    }

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Optional<Cart> getCart() {

        try {

            return Optional.of(
                    getCartOutOfContext()
            );
        } catch (AuthenticationCredentialsNotFoundException authException) {

            log.error("Trying to access cart of a non-authenticated user");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> getTotalNumberOfProductsInCart() {

        Optional<Cart> optCart = getCart();

        return optCart.map(
          cart -> cart.getCartItems()
                      .stream()
                      .mapToLong(CartItem::getQuantity)
                      .sum()
        );
    }

    @Override
    public Optional<CartItem> updateCartItemQuantity(
            Long cartItemId, Long quantity) {

        Cart cart = getCartOutOfContext();

        Optional<CartItem> optCartItemToUpdate =
                cart.getCartItems().stream()
                                   .filter(cartItem -> cartItem.getCartItemId().equals(cartItemId))
                                   .findAny();

        if (optCartItemToUpdate.isPresent()) {

            CartItem cartItemToUpdate = optCartItemToUpdate.get();
            cartItemToUpdate.setQuantity(quantity);

            cartRepository.updateCart(cart);

            return Optional.of(cartItemToUpdate);
        } else {
            log.error("Trying to update a non-existing cart item");
            return Optional.empty();
        }
   }

    @Override
    @Transactional
    public void applyCoupon(String couponCode, EntityProcessingResponse response) {

        Cart cart =
                appUserRetrievalService.getAppUserDetailsOutOfContext()
                                       .getAppUser()
                                       .getCart();

        Optional<Coupon> optCoupon =
                couponRepository.getCouponByValue(couponCode);

        if (optCoupon.isPresent()) {

            Coupon coupon = optCoupon.get();

            couponValidator.validate(coupon, response);

            if (response.hasErrors())
                return;

            cart.setDiscount(coupon.getDiscountPercentage());
            cartRepository.updateCart(cart);
            response.setSuccessCode("validation.coupon.success");
        } else
            response.addErrorCode("validation.coupon.notFound");
    }

    @Override
    public boolean removeCartItem(Long cartItemId) {

        Cart cart = getCartOutOfContext();

        Optional<CartItem> optCartItemToRemove =
                cart.getCartItems()
                    .stream()
                    .filter(cartItem -> cartItem.getCartItemId().equals(cartItemId))
                    .findAny();

        if (optCartItemToRemove.isPresent()) {

            CartItem cartItemToRemove = optCartItemToRemove.get();

            //There is a bug with PersistentSet in Hibernate that makes
            //using .contains() and .remove() methods of Set interface unpredictable.
            //This is a workaround: reset the whole set.
            cart.setCartItems(
                cart.getCartItems().stream()
                                   .filter(cartItem -> !cartItem.equals(cartItemToRemove))
                                   .collect(Collectors.toSet())
            );

            cartRepository.updateCart(cart);
            return true;

        } else
            return false;

    }

    @Override
    public boolean addCartItem(CartItemDTO cartItemDTO)
            throws AuthenticationCredentialsNotFoundException {

        AppUserDetails appUserDetails =
                appUserRetrievalService.getAppUserDetailsOutOfContext();

        AppUser appUser = appUserDetails.getAppUser();
        CartItem cartItem = extractCartItem(cartItemDTO);

        appUser.getCart().addCartItem(cartItem);

        return cartRepository.updateCart(appUser.getCart());
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

        return cartItem;
    }

    private Cart getCartOutOfContext() {

        return appUserRetrievalService.getAppUserDetailsOutOfContext()
                                      .getAppUser()
                                      .getCart();
    }

}
