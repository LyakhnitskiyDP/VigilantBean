package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.Product;
import com.ldp.vigilantBean.repository.Pagination;
import com.ldp.vigilantBean.repository.ProductRetrievalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@PropertySource("classpath:webappConfig.properties")
public class ProductRetrievalRepositoryImpl implements ProductRetrievalRepository {

    private static final Logger log =
            LogManager.getLogger(ProductRetrievalRepositoryImpl.class.getName());

    @Value("${interface.numberOfProductsPerPage}")
    private Integer numberOfProductsPerPage;

    private SessionFactory sessionFactory;

    public ProductRetrievalRepositoryImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllProducts(Pagination pagination) {

        try (Session session = sessionFactory.openSession()) {

            return session.getNamedQuery(Product.GET_ALL_PRODUCTS)
                          .setFirstResult(pagination.getFirstResultIndex() - 1)
                          .setMaxResults(pagination.getMaxResults())
                          .list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsByCategory(String category, Pagination pagination) {

        try (Session session = sessionFactory.openSession()) {

            return session.getNamedQuery(Product.GET_PRODUCTS_BY_CATEGORY)
                          .setFirstResult(pagination.getFirstResultIndex() - 1)
                          .setMaxResults(pagination.getMaxResults())
                          .setParameter("category", category)
                          .list();

        }

    }

    @Override
    public Long getNumberOfProductsByCategory(String category) {
        try (Session session = sessionFactory.openSession()) {

            return (Long) session.getNamedQuery(Product.GET_NUMBER_OF_PRODUCTS_BY_CATEGORY)
                                    .setParameter("category", category)
                                    .getSingleResult();

        }
    }

    @Override
    public Long getNumberOfAllProducts() {

        try (Session session = sessionFactory.openSession()) {

            return (Long) session.getNamedQuery(Product.GET_NUMBER_OF_PRODUCTS)
                                    .getSingleResult();

        }

    }

    @Override
    public Optional<Product> getProductById(Long id) {

        try (Session session = sessionFactory.openSession()) {

            Product product = (Product) session.getNamedQuery(Product.GET_PRODUCT_BY_ID)
                                               .setParameter("productId", id)
                                               .getSingleResult();

            return Optional.of(product);
        } catch (NoResultException noResultException) {

            log.info("Attempting to access non-existing product with id: " + id);

            return Optional.empty();
        }

    }



}
