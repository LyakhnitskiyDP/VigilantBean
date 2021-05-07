package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.domain.order.CartItem;
import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Optional;

public interface CartService {

    Optional<Long> getTotalNumberOfProductsInCart();

    boolean addCartItem(CartItemDTO cartItem)
            throws AuthenticationCredentialsNotFoundException;

    boolean removeCartItem(Long cartItemId);

    void applyCoupon(String coupon, EntityProcessingResponse response);

    Optional<CartItem> updateCartItemQuantity(Long cartItemId, Long quantity);

    Optional<Cart> getCart();
}
