package com.ldp.vigilantBean.domain.order;

import com.ldp.vigilantBean.domain.product.Product;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "product_id",
                nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id",
                nullable = false)
    private Cart cart;

    @Column(name = "quantity")
    private Long quantity;

    public BigDecimal getTotal() {

        return product.getUnitPrice()
                      .multiply(BigDecimal.valueOf(quantity));
    }

    public CartItem() {

    }

    public CartItem(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {

        if (quantity < 0)
            throw new IllegalArgumentException("Negative quantity");

        this.quantity = quantity;
    }

}
