package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserRetrievalServiceImpl implements AppUserRetrievalService {

    private static final Logger log =
            LogManager.getLogger(AppUserRetrievalServiceImpl.class.getName());

    private SessionFactory sessionFactory;

    public AppUserRetrievalServiceImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<AppUser> getAppUserByEmail(String email) {

        try (Session session = sessionFactory.openSession()) {

            return Optional.ofNullable(
                    (AppUser)
                    session.getNamedQuery(AppUser.GET_APP_USER_BY_EMAIL)
                           .setParameter("email", email)
                           .getSingleResult()
            );

        } catch (NonUniqueResultException nonUniqueResultException) {

           log.error("Data integrity violation: Email is not unique",
                     nonUniqueResultException);
           return Optional.empty();
        }

    }

}
