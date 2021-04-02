package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.comment.Comment;
import com.ldp.vigilantBean.domain.comment.CommentDTO;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.exception.ProductNotFoundException;
import com.ldp.vigilantBean.repository.CommentRepository;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.service.CommentService;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private ProductRetrievalService productRetrievalService;

    private AppUserRetrievalService appUserRetrievalService;

    public CommentServiceImpl(
            @Autowired
            CommentRepository commentRepository,
            @Autowired
            ProductRetrievalService productRetrievalService,
            @Autowired
            AppUserRetrievalService appUserRetrievalService) {

        this.productRetrievalService = productRetrievalService;
        this.appUserRetrievalService = appUserRetrievalService;
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean addComment(CommentDTO commentDTO) {

        Comment comment = initComment(commentDTO);

        return commentRepository.addComment(comment);
    }

    private Comment initComment(CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setAppUser(appUserRetrievalService.getAppUserDetailsOutOfContext().getAppUser());

        Optional<Product> optCommentedProduct =
                productRetrievalService.getProductById(commentDTO.getProductId());
        if (!optCommentedProduct.isPresent())
            throw new ProductNotFoundException(commentDTO.getProductId());

        comment.setProduct(optCommentedProduct.get());
        comment.setStars(commentDTO.getStars());

        return comment;
    }

}
