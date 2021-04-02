package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.comment.CommentDTO;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Optional;

@Component
public class NewCommentValidator extends NewEntityValidator<CommentDTO> {

    private ProductRetrievalService productRetrievalService;

    public NewCommentValidator(
            @Autowired
            @Qualifier("beanValidator")
            Validator validator,
            @Autowired
            ProductRetrievalService productRetrievalService) {

        super(validator);
        this.productRetrievalService = productRetrievalService;
    }

    @Override
    protected void validateConsistency(
            CommentDTO entity,
            EntityProcessingResponse response) {

        checkProductExistence(entity.getProductId(), response);

    }

    private void checkProductExistence(
            Long productId,
            EntityProcessingResponse response) {

        Optional<Product> optProduct =
                productRetrievalService.getProductById(productId);

        if (!optProduct.isPresent())
            response.addErrorCode("validation.comment.productNotFound");
    }

}
