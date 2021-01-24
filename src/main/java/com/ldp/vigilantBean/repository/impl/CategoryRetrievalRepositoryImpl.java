package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.Category;
import com.ldp.vigilantBean.repository.CategoryRetrievalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRetrievalRepositoryImpl implements CategoryRetrievalRepository {

    private static final Logger log =
            LogManager.getLogger(CategoryRetrievalRepositoryImpl.class.getName());

    private SessionFactory sessionFactory;

    public CategoryRetrievalRepositoryImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {

        try (Session session = sessionFactory.openSession()) {

            return Optional.ofNullable(
                            (Category) session.getNamedQuery(Category.GET_CATEGORY_BY_NAME)
                                              .setReadOnly(true)
                                              .setParameter("categoryName", categoryName)
                                              .getSingleResult()
                            );

        } catch (NoResultException noResultException) {

            log.info("Attempting to access non-existing category: " + categoryName);

            return Optional.empty();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {

        try (Session session = sessionFactory.openSession()) {

            return session.getNamedQuery(Category.GET_ALL_CATEGORIES)
                          .setReadOnly(true)
                          .list();

        }

    }
}
