package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
class AppUserRetrievalRepositoryImpl implements AppUserRetrievalRepository {

    private static final Logger log =
            LogManager.getLogger(AppUserRetrievalRepositoryImpl.class.getName());

    private SessionFactory sessionFactory;

    public AppUserRetrievalRepositoryImpl(
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
        } catch (NoResultException noResultException) {

            return Optional.empty();
        }

    }
}
