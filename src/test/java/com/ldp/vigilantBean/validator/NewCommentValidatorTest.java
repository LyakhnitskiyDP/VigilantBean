package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.comment.Comment;
import com.ldp.vigilantBean.domain.comment.CommentDTO;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import javax.validation.Validation;

import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class NewCommentValidatorTest {

    private NewCommentValidator validator;

    private javax.validation.Validator beanValidator;

    private ProductRetrievalService productRetrievalService;

    private EntityProcessingResponse response;


    @BeforeEach
    public void setUp() {

        beanValidator =
                Validation.buildDefaultValidatorFactory().getValidator();

        productRetrievalService =
                mock(ProductRetrievalService.class);

        response = new EntityProcessingResponse(
                        new Locale("en", "US"),
                        Mockito.mock(MessageSource.class)
                   );

        validator = new NewCommentValidator(beanValidator, productRetrievalService);
    }

    @Test
    @DisplayName("Should invalidate based on constraint violations")
    public void testConstraintValidation() {

        CommentDTO commentDTO = CommentDTO.builder()
                                            .content("")
                                            .stars((byte) 123)
                                            .productId(1L)
                                          .build();
        when(productRetrievalService.getProductById(1L))
                .thenReturn(Optional.of(mock(Product.class)));

        validator.validate(commentDTO, response);

        assertTrue(response.hasErrors());
        assertEquals(response.getErrorCodes().size(), 2);
        org.assertj.core.api.Assertions.assertThat(
                response.getErrorCodes()).asList().contains(
                    "validation.comment.blank",
                    "validation.comment.tooMuchStars"
                );
        verify(productRetrievalService).getProductById(1L);
    }

    @Test
    @DisplayName("Should invalidate based on product absence")
    public void testProductNotFound() {

        CommentDTO commentDTO = CommentDTO.builder()
                                            .content("A valid comment")
                                            .stars((byte) 4)
                                            .productId(1L)
                                          .build();
        when(productRetrievalService.getProductById(1L))
                .thenReturn(Optional.empty());

        validator.validate(commentDTO, response);

        assertTrue(response.hasErrors());
        assertEquals(response.getErrorCodes().size(), 1);
        assertEquals(response.getErrorCodes().get(0), "validation.comment.productNotFound");
    }



}















