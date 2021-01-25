package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class AppUserRetrievalRepositoryImpl implements AppUserRetrievalRepository {

    private SessionFactory sessionFactory;

    public AppUserRetrievalRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) {

        try (Session session = sessionFactory.openSession()) {

            AppUser user = session.createNamedQuery(AppUser.GET_APP_USER_BY_USERNAME, AppUser.class)
                                  .setParameter("username", username)
                                  .getSingleResult();

            return Optional.of(user);
        } catch (NoResultException noResultException) {

            return Optional.empty();
        }

    }
}
