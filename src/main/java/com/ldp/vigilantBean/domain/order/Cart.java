package com.ldp.vigilantBean.domain.order;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class Cart {

    @Id
    @Column(name = "cart_id")
    private Long cartId;

    @OneToMany(mappedBy = "cart",
               cascade = CascadeType.ALL,
               fetch = FetchType.EAGER,
               orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<CartItem> cartItems;

    @OneToOne(mappedBy = "cart")
    private AppUser appUser;

    private Byte discount;

    /**
     * Calculates sum of all Cart Items and applies a discount
     * @return Grand Total of a cart
     */
    public BigDecimal getGrandTotal() {

        // Get prices of all Cart Items
        BigDecimal grandTotal =
        cartItems.stream()
                .map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Apply a discount
        BigDecimal grandTotalWithDiscount =
                grandTotal.subtract( getDiscountValue(grandTotal) );

        return grandTotalWithDiscount;
    }

    private BigDecimal getDiscountValue(BigDecimal initialValue) {

       return initialValue.multiply(BigDecimal.valueOf(((double) discount) / 100));
    }

    public Cart() {

    }

    public Cart(Set<CartItem> cartItems, Byte discount) {
        this.cartItems = cartItems;
        this.setDiscount(discount);
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public boolean addCartItem(CartItem cartItem) {

        cartItem.setCart(this);
        return cartItems.add(cartItem);
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {

        if (discount < 1 || discount > 99)
            throw new IllegalArgumentException("Invalid discount number");

        this.discount = discount;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
