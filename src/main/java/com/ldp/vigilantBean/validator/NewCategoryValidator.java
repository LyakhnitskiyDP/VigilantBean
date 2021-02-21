package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.FormProcessingResponse;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NewCategoryValidator {

    private static final Logger log =
            LogManager.getLogger(NewCategoryValidator.class.getName());

    private javax.validation.Validator beanValidator;

    private CategoryRetrievalService categoryRetrievalService;

    public NewCategoryValidator(
            @Autowired
            CategoryRetrievalService categoryRetrievalService,
            @Autowired
            @Qualifier("beanValidator")
            javax.validation.Validator beanValidator) {

        this.beanValidator = beanValidator;
        this.categoryRetrievalService = categoryRetrievalService;
    }


    public void validate
            (CategoryDTO categoryDTO,
             FormProcessingResponse response) {

        checkForConstraintViolations(categoryDTO, response);

        checkForNameUniqueness(categoryDTO, response);

        checkForPicturePresence(categoryDTO, response);
    }

    private void checkForConstraintViolations
            (CategoryDTO categoryDTO, FormProcessingResponse response) {

        Set<ConstraintViolation<CategoryDTO>> violations =
                beanValidator.validate(categoryDTO);
        if (violations.size() > 0) {

            response.addErrorCodes(
                    violations.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList())
            );
        }
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

    private void checkForPicturePresence
            (CategoryDTO categoryDTO, FormProcessingResponse response) {

        if (categoryDTO.getPicture() == null || categoryDTO.getPicture().isEmpty()) {

            response.addErrorCode(
                    "validation.newCategory.categoryPictureIsAbsent"
            );
        }
    }

}
