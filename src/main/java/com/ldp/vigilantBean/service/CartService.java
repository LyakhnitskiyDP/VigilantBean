package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.order.CartItemDTO;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public interface CartService {

    boolean addCartItem(CartItemDTO cartItem) throws AuthenticationCredentialsNotFoundException;

}
