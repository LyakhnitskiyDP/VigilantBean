package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.repository.CartRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class CartRepositoryImpl implements CartRepository {

    private SessionFactory sessionFactory;

    public CartRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean updateCart(Cart cart) {

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            session.saveOrUpdate(cart);

            session.getTransaction().commit();
            return true;
        } catch (HibernateException hibernateException) {
            return false;
        }

    }


}


