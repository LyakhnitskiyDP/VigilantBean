package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.comment.Comment;
import com.ldp.vigilantBean.domain.comment.CommentDTO;

import java.util.List;

public interface CommentRepository {

    boolean addComment(CommentDTO commentDTO);

    List<Comment> getComments(Long productId, Pagination pagination);
}
