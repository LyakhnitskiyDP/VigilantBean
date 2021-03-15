package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.controller.extractor.ProductDTOExtractor;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import com.ldp.vigilantBean.service.ProductAlterService;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import com.ldp.vigilantBean.validator.NewProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Locale;
import java.util.Optional;

/**
 *
 * Sub-controller for
 */
@Component
public class ProductController {

    private ProductDTOExtractor productDTOExtractor;

    private NewProductValidator productValidator;

    private ProductAlterService productAlterService;

    private MessageSource messageSource;

    public ProductController(
            @Autowired
            ProductDTOExtractor productDTOExtractor,
            @Autowired
            NewProductValidator productValidator,
            @Autowired
            ProductAlterService productAlterService) {

        this.productDTOExtractor = productDTOExtractor;
        this.productValidator = productValidator;
        this.productAlterService = productAlterService;
    }

    @Autowired
    private void setMessageSource(MessageSource messageSource) {

        this.messageSource = messageSource;
    }

    public ResponseEntity<EntityProcessingResponse> processNewProduct(
            MultipartHttpServletRequest request) {

        ProductDTO productDTO =
                productDTOExtractor.extractEntity(request);

        EntityProcessingResponse response =
                initFormProcessingResponse(request.getLocale());

        productValidator.validate(productDTO, response);
        response.externalizeMessages();

        if (response.hasErrors())
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        Optional<Product> savedProduct =
                productAlterService.addNewProduct(productDTO);

        if (savedProduct.isPresent())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private EntityProcessingResponse initFormProcessingResponse(Locale locale) {

        EntityProcessingResponse response =
                new EntityProcessingResponse(locale, this.messageSource);
        response.setSuccessCode("view.admin.addProduct.success");

        return response;
    }
}
