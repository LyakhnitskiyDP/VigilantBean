package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.order.Coupon;
import com.ldp.vigilantBean.repository.CouponRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Optional;

@Repository
@Log4j2
class CouponRepositoryImpl implements CouponRepository {

    private SessionFactory sessionFactory;

    public CouponRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Coupon> getCouponByValue(String couponValue) {

        try (Session session = sessionFactory.openSession()) {

            return Optional.of(
                (Coupon)
                session.getNamedQuery(Coupon.GET_COUPON_BY_VALUE)
                       .setParameter("couponValue", couponValue)
                       .getSingleResult()
            );
        } catch (NonUniqueResultException nonUniqueResultException) {

            log.error("Constraint violation of Coupon table: coupon value must be unique");
            return Optional.empty();
        } catch (NoResultException noResultException) {

            log.info("Coupon not found: " + couponValue);
            return Optional.empty();
        }
    }

}
