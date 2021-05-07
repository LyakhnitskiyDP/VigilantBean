package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.order.Coupon;

import java.util.Optional;

public interface CouponRepository {

    Optional<Coupon> getCouponByValue(String couponValue);

}
