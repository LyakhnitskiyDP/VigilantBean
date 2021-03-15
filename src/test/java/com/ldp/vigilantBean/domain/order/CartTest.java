package com.ldp.vigilantBean.domain.order;

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
               expectedPrice.multiply(BigDecimal.valueOf((double) discount / 100))
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

}
