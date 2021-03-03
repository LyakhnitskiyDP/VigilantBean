package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class NewCategoryValidator extends NewEntityValidator<CategoryDTO> {

    private static final Logger log =
            LogManager.getLogger(NewCategoryValidator.class.getName());

    private CategoryRetrievalService categoryRetrievalService;

    public NewCategoryValidator(
            @Autowired
            CategoryRetrievalService categoryRetrievalService) {

        this.categoryRetrievalService = categoryRetrievalService;
    }

    @Override
    @Autowired
    @Qualifier("beanValidator")
    public void setBeanValidator(Validator beanValidator) {

        super.beanValidator = beanValidator;
    }

    @Override
    public void validate
            (CategoryDTO categoryDTO,
             FormProcessingResponse response) {

        super.checkForConstraintViolations(categoryDTO, response);

        checkForNameUniqueness(categoryDTO, response);

        checkPicture(categoryDTO, response);

        response.setSuccessCode("view.admin.addCategory.success");
    }

    private void checkForNameUniqueness
            (CategoryDTO categoryDTO, FormProcessingResponse response) {

        log.info("Check category name: " + categoryDTO.getName());

        if (categoryRetrievalService.getCategoryByName(categoryDTO.getName()).isPresent()) {

            response.addErrorCode(
                    "validation.newCategory.categoryNameExists"
            );
            log.info("Name of a new Category is already taken");
        }
    }

    private void checkPicture
            (CategoryDTO categoryDTO, FormProcessingResponse response) {

        if (categoryDTO.getPicture() == null || categoryDTO.getPicture().isEmpty()) {

            response.addErrorCode(
                    "validation.newCategory.categoryPictureIsAbsent"
            );
            return;
        }

        if (!categoryDTO.getPicture().getContentType().startsWith("image")) {

            response.addErrorCode(
                    "validation.newCategory.categoryPictureIsInvalid"
            );

        }

    }

}
