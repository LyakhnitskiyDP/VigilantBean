package com.ldp.vigilantBean.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ldp.vigilantBean.domain.appUser.AppUser;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    @EqualsAndHashCode.Include
    private Long cartId;

    @OneToMany(mappedBy = "cart",
               cascade = CascadeType.ALL,
               fetch = FetchType.EAGER,
               orphanRemoval = true)
    private Set<CartItem> cartItems;

    @OneToOne(mappedBy = "cart")
    @EqualsAndHashCode.Include
    private AppUser appUser;

    @JsonIgnore
    private Byte discount = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applied_coupon_id")
    private Coupon appliedCoupon;

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
                grandTotal.subtract( getDiscountValue(grandTotal) )
                          .setScale(2, RoundingMode.HALF_EVEN);

        return grandTotalWithDiscount;
    }

    public BigDecimal getGrandTotalWithoutDiscount() {

        return cartItems.stream()
                        .map(CartItem::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDiscountValue(BigDecimal initialValue) {

       return initialValue.multiply(BigDecimal.valueOf(((double) (discount == null ? 0 : discount)) / 100));
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

    public boolean addCartItem(CartItem cartItemToAdd) {

        Optional<CartItem> optSameProductCartItem =
                this.cartItems.stream()
                              .filter(
                                      cartItem ->
                                              cartItem.getProduct().equals(cartItemToAdd.getProduct())
                              )
                              .findAny();

        if (optSameProductCartItem.isPresent()) {

            CartItem sameProductCartItem = optSameProductCartItem.get();

            sameProductCartItem.setQuantity(
                    sameProductCartItem.getQuantity() + cartItemToAdd.getQuantity()
            );

            return true;
        } else {

            cartItemToAdd.setCart(this);
            return cartItems.add(cartItemToAdd);
        }
    }

    public boolean removeCartItem(CartItem cartItem) {

        return cartItems.remove(cartItem);
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {

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
