package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.controller.entityExtractor.CategoryDTOExtractor;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.service.CategoryAlterService;
import com.ldp.vigilantBean.validator.FormProcessingResponse;
import com.ldp.vigilantBean.validator.NewCategoryValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Locale;
import java.util.Optional;

@Component
@Log4j2
public class CategoryController {

    private CategoryDTOExtractor categoryDTOExtractor;

    private NewCategoryValidator categoryValidator;

    private CategoryAlterService categoryAlterService;

    private MessageSource messageSource;

    public CategoryController(
            @Autowired
            CategoryDTOExtractor categoryDTOExtractor,
            @Autowired
            NewCategoryValidator categoryValidator,
            @Autowired
            CategoryAlterService categoryAlterService) {

        this.categoryDTOExtractor = categoryDTOExtractor;
        this.categoryValidator = categoryValidator;
        this.categoryAlterService = categoryAlterService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<FormProcessingResponse> processNewCategory(
            MultipartHttpServletRequest request) {

        CategoryDTO categoryDTO =
                categoryDTOExtractor.extractEntity(request);

        FormProcessingResponse formResponse =
                initFormProcessingResponse(request.getLocale());

        categoryValidator.validate(categoryDTO, formResponse);

        formResponse.externalizeMessages();

        if (formResponse.hasErrors())
            return new ResponseEntity<>(formResponse, HttpStatus.BAD_REQUEST);

        Optional<Category> category =
                categoryAlterService.addNewCategory(categoryDTO);

        if (category.isPresent())
            return new ResponseEntity<FormProcessingResponse>(formResponse, HttpStatus.OK);
        else {
            log.error("Exception while saving validated category");
            return new ResponseEntity<FormProcessingResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private FormProcessingResponse initFormProcessingResponse(Locale locale) {

        FormProcessingResponse response =
                new FormProcessingResponse(locale, this.messageSource);
        response.setSuccessCode("view.admin.addCategory.success");

        return response;
    }

}
