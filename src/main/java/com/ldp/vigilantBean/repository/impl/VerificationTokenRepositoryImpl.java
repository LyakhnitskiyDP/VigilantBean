package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.repository.VerificationTokenRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Repository
class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

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


    @Override
    public Optional<VerificationToken> get(String token) {

        try (Session session = sessionFactory.openSession()) {


            Query query = session.getNamedQuery(VerificationToken.GET_BY_TOKEN)
                                 .setParameter("token", token);

            Optional<VerificationToken> optVerificationToken =
                    Optional.ofNullable((VerificationToken) query.getSingleResult());

            return optVerificationToken;
        } catch (NoResultException noResultException) {

            return Optional.empty();
        }

    }
}
