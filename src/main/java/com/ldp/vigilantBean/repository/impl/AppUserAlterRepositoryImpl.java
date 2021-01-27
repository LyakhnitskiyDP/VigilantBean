package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AppUserAlterRepositoryImpl implements AppUserAlterRepository {

    private SessionFactory sessionFactory;

    public AppUserAlterRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

       this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<AppUser> signUpUser(AppUser appUser) {

        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();

            session.save(appUser);

            tx.commit();

            return Optional.of(appUser);
        } 
    }

}
