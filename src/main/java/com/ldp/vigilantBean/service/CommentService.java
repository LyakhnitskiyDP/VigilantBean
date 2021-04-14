package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.comment.Comment;
import com.ldp.vigilantBean.domain.comment.CommentDTO;
import com.ldp.vigilantBean.repository.PaginatedEntities;

import java.util.List;

public interface CommentService {

    boolean addComment(CommentDTO commentDTO);

    PaginatedEntities<Comment> getComments(Long productId,
                                           Integer pageNumber,
                                           Integer commentsPerPage);

}
