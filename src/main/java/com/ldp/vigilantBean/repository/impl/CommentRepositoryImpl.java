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

import java.util.Collections;
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

       try (Session session = sessionFactory.openSession()) {

           Transaction tx = session.getTransaction();
           tx.begin();

           session.save(comment);

           tx.commit();
           return true;
       } catch (HibernateException hibernateException) {

           log.error("Error while saving a comment ", hibernateException);
           return false;
       }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Comment> getComments(Long productId, Pagination pagination) {

        try (Session session = sessionFactory.openSession()) {

            List<Comment> comments = session.getNamedQuery(Comment.GET_ALL_COMMENTS)
                                            .setParameter("productId", productId)
                                            .setFirstResult(pagination.getFirstResultIndex() - 1)
                                            .setMaxResults(pagination.getMaxResults())
                                            .list();
            return comments;
        } catch (HibernateException hibernateException) {
            log.error("Exception while querying comments", hibernateException);
            return Collections.emptyList();
        }
    }

    @Override
    public Long getNumberOfComments(Long productId) {

        try (Session session = sessionFactory.openSession()) {

            return (Long) session.getNamedQuery(Comment.GET_NUMBER_OF_COMMENTS)
                                 .setParameter("productId", productId)
                                 .getSingleResult();

        }

    }
}
