package com.ldp.vigilantBean.domain.order;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_order")
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private AppUser customer;

    private Cart cart;

    private Date dateOfCreation;

    private Date dateOfArrival;

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
}
