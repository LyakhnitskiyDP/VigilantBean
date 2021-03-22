package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.order.Cart;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CartRepository {

     boolean updateCart(Cart cart);

}
