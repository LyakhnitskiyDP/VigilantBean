package com.ldp.vigilantBean.repository.impl;

import com.ldp.vigilantBean.domain.comment.Comment;
import com.ldp.vigilantBean.repository.CommentRepository;
import com.ldp.vigilantBean.repository.Pagination;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
class CommentRepositoryImpl implements CommentRepository {

    private SessionFactory sessionFactory;

    public CommentRepositoryImpl(
            @Autowired
            SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addComment(Comment comment) {

       Transaction tx = null;
       try (Session session = sessionFactory.openSession()) {

           tx = session.getTransaction();
           tx.begin();

           session.save(comment);

           tx.commit();
           return true;
       } catch (HibernateException hibernateException) {

           tx.rollback();
           log.error("Error while saving a comment ", hibernateException);
           return false;
       }

    }

    @Override
    public List<Comment> getComments(Long productId, Pagination pagination) {
        return null;
    }
}
