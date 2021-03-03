package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.repository.ProductAlterRepository;
import com.ldp.vigilantBean.utils.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductAlterRepositoryImpl implements ProductAlterRepository {

    private SessionFactory sessionFactory;

    public ProductAlterRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

       this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Product> addNewProduct(Product product) {

        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            session.save(product);

            session.getTransaction().commit();
        }

        return Optional.of(product);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {

        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            session.merge(product);

            session.getTransaction().commit();
        }

        return Optional.of(product);

    }
}
