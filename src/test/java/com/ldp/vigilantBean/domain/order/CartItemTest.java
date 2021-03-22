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
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class CartItemTest {

    private Product mockProduct;

    private Long quantity;

    private CartItem cartItem;

    @BeforeEach
    public void setUp() {

        this.mockProduct = Mockito.mock(Product.class);

        cartItem = new CartItem(mockProduct, 0L);
    }

    @ParameterizedTest()
    @CsvSource({"12.40, 5", "0.00, 12", "100.00, 100"})
    @DisplayName("Should calculate valid total")
    public void calculateValidTotal(double productPrice, long quantity) {

        Mockito.when(mockProduct.getUnitPrice())
               .thenReturn(BigDecimal.valueOf(productPrice));

        cartItem.setQuantity(quantity);

        Assertions.assertEquals(
            BigDecimal.valueOf(productPrice * quantity),
            cartItem.getTotal()
        );
        Mockito.verify(mockProduct).getUnitPrice();
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException if quantity is negative")
    public void throwExceptionOnNegativeQuantity() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> cartItem.setQuantity(-1L)
        );
    }

}
