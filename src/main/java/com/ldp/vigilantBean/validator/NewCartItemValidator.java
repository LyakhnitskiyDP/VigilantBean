package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class NewCartItemValidator extends NewEntityValidator<CartItemDTO> {

    private ProductRetrievalService productRetrievalService;

    public NewCartItemValidator(
            @Autowired
            @Qualifier("beanValidator")
            Validator validator) {

        super(validator);
    }

    @Autowired
    public void setProductRetrievalService(ProductRetrievalService service) {

        this.productRetrievalService = service;
    }

    @Override
    protected void validateConsistency(
            CartItemDTO cartItemDTO,
            EntityProcessingResponse response) {

        checkProductExistence(cartItemDTO.getProductId(), response);

        if (cartItemDTO.getQuantity() > 1)
            response.setSuccessCode("validation.cart.addProducts.success");
        else
            response.setSuccessCode("validation.cart.addProduct.success");
    }


    private void checkProductExistence(
            Long productId,
            EntityProcessingResponse response) {

        if (!productRetrievalService.getProductById(productId).isPresent())
            response.addErrorCode("validation.cart.noSuchProduct");

    }
}
