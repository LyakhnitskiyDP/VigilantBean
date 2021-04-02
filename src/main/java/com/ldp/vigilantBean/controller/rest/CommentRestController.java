package com.ldp.vigilantBean.controller.rest;

import com.ldp.vigilantBean.controller.extractor.ParamExtractor;
import com.ldp.vigilantBean.domain.comment.CommentDTO;
import com.ldp.vigilantBean.service.CommentService;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import com.ldp.vigilantBean.validator.NewCommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/api/comment")
public class CommentRestController {

    private NewCommentValidator commentValidator;
    private CommentService commentService;
    private MessageSource messageSource;

    public CommentRestController(
            @Autowired
            NewCommentValidator commentValidator,
            @Autowired
            CommentService commentService) {

        this.commentValidator = commentValidator;
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<EntityProcessingResponse> addComment(
            HttpServletRequest request) {

        CommentDTO commentDTO = extractCommentDTO(request);
        EntityProcessingResponse response = initEntityProcessingResponse(request);

        commentValidator.validate(commentDTO, response);

        if (response.hasErrors())
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        if (commentService.addComment(commentDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private CommentDTO extractCommentDTO(HttpServletRequest request) {

        return CommentDTO.builder()
                            .productId(ParamExtractor.safelyExtractLong(request, "productId"))
                            .content(request.getParameter("content"))
                            .stars(ParamExtractor.safelyExtractByte(request, "stars"))
                         .build();
    }

    private EntityProcessingResponse initEntityProcessingResponse(
            HttpServletRequest request ) {

        return new EntityProcessingResponse(request.getLocale(), messageSource);
    }

    @Autowired
    private void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
