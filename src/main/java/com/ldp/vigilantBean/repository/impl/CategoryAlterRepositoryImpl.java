package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.repository.CategoryAlterRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class CategoryAlterRepositoryImpl implements CategoryAlterRepository {

    private static final Logger log =
            LogManager.getLogger(CategoryAlterRepositoryImpl.class.getName());

    private SessionFactory sessionFactory;

    public CategoryAlterRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Category> addNewCategory(Category category) {

        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            session.persist(category);

            session.getTransaction().commit();
        } catch (Exception e) {

            log.error("Exception while adding a new category", e);

            return Optional.empty();
        }

        return Optional.of(category);
    }
}
