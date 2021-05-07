package com.ldp.vigilantBean.domain.order;

import com.ldp.vigilantBean.domain.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class CartTest {

    private Cart cart;

    private Set<CartItem> cartItems =
            new HashSet<>();

    private static final BigDecimal CART_ITEM_TOTAL =
            BigDecimal.valueOf(20.00);

    @BeforeEach
    public void setUp() {

        cart = new Cart();

        IntStream.range(0, 10)
                 .forEach((i) -> {
                             CartItem mockCartItem = mock(CartItem.class);
                             when(mockCartItem.getTotal()).thenReturn(CART_ITEM_TOTAL);
                             cartItems.add(mockCartItem);
                         }
                 );

        cart.setCartItems(cartItems);
    }

    @ParameterizedTest
    @ValueSource(bytes = {1, 25, 50, 66, 75, 99})
    @DisplayName("Should calculate valid Grand Totals")
    public void shouldCalculateValidGrandTotals(byte discount) {

       cart.setDiscount(discount);

       BigDecimal expectedPrice = cartItems.stream()
                                           .map(CartItem::getTotal)
                                           .reduce(BigDecimal.ZERO, BigDecimal::add);

       expectedPrice = expectedPrice.subtract(
               expectedPrice.multiply(BigDecimal.valueOf((double) discount / 100)).setScale(2, RoundingMode.HALF_EVEN)
       );

       Assertions.assertEquals(
               expectedPrice,
               cart.getGrandTotal()
       );
   }

   @ParameterizedTest
   @ValueSource(bytes = {-20, 0})
   @DisplayName("Should throw an Exception when discount is invalid")
   public void throwExceptionOnNegativeDiscount(byte discount) {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> cart.setDiscount(discount)
        );
   }

   @Test
   @DisplayName("Should increase quantity of a cart item when existing cart item is added")
   public void shouldIncreaseQuantityWhenExistingCartItemAdded() {

        Product theSameProduct = mock(Product.class);

        CartItem cartItem_1 = new CartItem();
        cartItem_1.setProduct(theSameProduct);
        cartItem_1.setQuantity(2L);

        CartItem cartItem_2 = new CartItem();
        cartItem_2.setProduct(theSameProduct);
        cartItem_2.setQuantity(3L);

        cart.setCartItems(new HashSet<>());
        cart.addCartItem(cartItem_1);
        cart.addCartItem(cartItem_2);

        Assertions.assertEquals(1, cart.getCartItems().size());
        Assertions.assertEquals(5L, cart.getCartItems()
                                                .stream()
                                                .findAny()
                                                .get()
                                                .getQuantity()
        );

   }

}
