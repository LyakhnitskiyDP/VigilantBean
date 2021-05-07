package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.order.Coupon;
import com.ldp.vigilantBean.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Optional;

@Component
public class CouponValidator extends NewEntityValidator<Coupon> {

    private CouponRepository couponRepository;

    public CouponValidator(
            @Autowired
            @Qualifier("beanValidator")
            Validator validator,
            @Autowired
            CouponRepository couponRepository) {
        super(validator);

        this.couponRepository = couponRepository;
    }

    @Override
    protected void validateConsistency(
            Coupon entity,
            EntityProcessingResponse response) {

    }
}
