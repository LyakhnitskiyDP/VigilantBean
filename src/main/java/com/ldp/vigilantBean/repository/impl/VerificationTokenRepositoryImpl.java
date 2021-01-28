package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    private SessionFactory sessionFactory;

    public VerificationTokenRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

       this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<VerificationToken> create(VerificationToken token) {

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.save(token);

            tx.commit();

            return Optional.of(token);
        }

    }

}
