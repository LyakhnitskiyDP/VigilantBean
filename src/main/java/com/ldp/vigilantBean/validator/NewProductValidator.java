package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.product.ProductDTO;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class NewProductValidator extends NewEntityValidator <ProductDTO> {

    private ProductRetrievalService productRetrievalService;

    public NewProductValidator(
            @Autowired
            ProductRetrievalService productRetrievalService) {

       this.productRetrievalService = productRetrievalService;
    }

    @Override
    @Autowired
    @Qualifier("beanValidator")
    protected void setBeanValidator(Validator validator) {

        super.beanValidator = validator;
    }

    @Override
    public void validate(ProductDTO productDTO, FormProcessingResponse response) {

        super.checkForConstraintViolations(productDTO, response);
        checkPictures(productDTO, response);

    }

    private void checkPictures(
            ProductDTO productDTO,
            FormProcessingResponse response) {

        if (productDTO.getPrimaryPicture() == null || productDTO.getPrimaryPicture().isEmpty()) {

            response.addErrorCode("validation.newProduct.mainPictureIsAbsent");
            return;
        }

        if (!productDTO.getPrimaryPicture().getContentType().startsWith("image")) {

            response.addErrorCode("validation.newProduct.mainPictureIsInvalid");
            return;
        }

        boolean invalidSecondaryPictureFound =
                productDTO.getSecondaryPictures().stream()
                                                 .anyMatch(
                                                         picture -> !picture.getContentType().startsWith("image")
                                                 );

        if (invalidSecondaryPictureFound) {
            response.addErrorCode("validation.newProduct.secondaryPicturesAreInvalid");
            return;
        }
    }
}
