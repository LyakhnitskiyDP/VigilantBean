package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import org.apache.logging.log4j.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AppUserAlterRepositoryImpl implements AppUserAlterRepository {

    private static final Logger log =
            LogManager.getLogger(AppUserAlterRepositoryImpl.class.getName());

    private SessionFactory sessionFactory;

    public AppUserAlterRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

       this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<AppUser> registerUser(AppUser appUser) {

        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();

            session.save(appUser);

            tx.commit();

            return Optional.of(appUser);
        }
    }

    @Override
    public boolean enableUser(AppUser appUser) {

        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("update AppUser set enabled = true " +
                                                "where appUserId = :appUserId")
                                                .setParameter("appUserId", appUser.getAppUserId());

            int rowsUpdated = query.executeUpdate();

            if (rowsUpdated > 1) {

                log.error("More then one row was update with app user id: " + appUser.getAppUserId());

                tx.rollback();
                return false;
            }

            if (rowsUpdated == 0) {

                log.error("Cannot enable non-existing user with id: " + appUser.getAppUserId());

                tx.rollback();
                return false;
            }

            tx.commit();
            return true;
        }

    }
}
