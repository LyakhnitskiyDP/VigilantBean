package com.ldp.vigilantBean.validator;

import com.ldp.vigilantBean.domain.FormProcessingResponse;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.utils.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.Validation;
import java.util.Optional;

public class NewCategoryValidatorTest {

    private NewCategoryValidator newCategoryValidator;

    private CategoryRetrievalService categoryRetrievalService;

    private javax.validation.Validator beanValidator;

    private MockMultipartFile validPhoto = new MockMultipartFile("categoryPhoto",
            "photo.jpg",
            "image/jpg",
            "fakePic".getBytes());

    private MockMultipartFile invalidPhoto = new MockMultipartFile("categoryPhoto",
            "photo.jpg",
            "text/html",
            "fakePic".getBytes());

    private CategoryDTO validCategoryDTO;

    private FormProcessingResponse response;

    @BeforeEach
    public void setUp() {

        categoryRetrievalService =
                Mockito.mock(CategoryRetrievalService.class);

        beanValidator =
                Validation.buildDefaultValidatorFactory().getValidator();

        this.newCategoryValidator =
                new NewCategoryValidator(categoryRetrievalService, beanValidator);

        response = new FormProcessingResponse();

        this.validCategoryDTO = new CategoryDTO();
        validCategoryDTO.setDescription("A proper description with length of permitted size");
        validCategoryDTO.setName("Valid Name");
        validCategoryDTO.setPicture(validPhoto);
    }

    @Test
    @DisplayName("Should validate CategoryDTO")
    public void testWithValidCategoryDTO() {

        Mockito.when(categoryRetrievalService.getCategoryByName("Valid Name"))
               .thenReturn(Optional.empty());

        newCategoryValidator.validate(validCategoryDTO, response);

        Assertions.assertFalse(response.hasErrors());
    }

    @Test
    @DisplayName("Should invalidate categoryDTO with invalid photo")
    public void testWithInvalidPhoto() {

        CategoryDTO categoryDTO = this.validCategoryDTO;
        categoryDTO.setPicture(invalidPhoto);

        Mockito.when(categoryRetrievalService.getCategoryByName("Valid Name"))
                .thenReturn(Optional.empty());

        newCategoryValidator.validate(categoryDTO, response);

        Assertions.assertTrue(response.hasErrors());

        Assertions.assertTrue(
                response.getErrorCodes().contains("validation.newCategory.categoryPictureIsInvalid")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "way too long category name that shouldn't be allowed"})
    @DisplayName("Should invalidate categoryDTO with invalid names")
    public void shouldInvalidateCategoryDTOWithInvalidNames(String invalidCategoryName) {

        CategoryDTO categoryDTO = this.validCategoryDTO;
        categoryDTO.setName(invalidCategoryName);

        Mockito.when(categoryRetrievalService.getCategoryByName("Valid Name"))
                .thenReturn(Optional.empty());

        newCategoryValidator.validate(categoryDTO, response);

        Assertions.assertTrue(response.hasErrors());

        Assertions.assertTrue(
                response.getErrorCodes().contains("validation.newCategory.nameOutOfRange")
        );
    }

    @Test
    @DisplayName("Should invalidate categoryDTO with invalid descriptions")
    public void shouldInvalidateCategoryDTOWithInvalidDescription() {

        String longCategoryDesc = StringUtil.generateStringOfSize(220);

        CategoryDTO categoryDTO = this.validCategoryDTO;
        categoryDTO.setDescription(longCategoryDesc);

        Mockito.when(categoryRetrievalService.getCategoryByName("Valid Name"))
                .thenReturn(Optional.empty());

        newCategoryValidator.validate(categoryDTO, response);

        Assertions.assertTrue(response.hasErrors());

        Assertions.assertTrue(
                response.getErrorCodes().contains("validation.newCategory.descriptionOutOfRange")
        );
    }

    @Test
    @DisplayName("Should invalidate categoryDTO which name is already taken")
    public void shouldInvalidateCategoryDTOWithTakenCategoryName() {

        Category existingCategory = new Category();

        Mockito.when(categoryRetrievalService.getCategoryByName("Valid Name"))
                .thenReturn(Optional.of(existingCategory));

        newCategoryValidator.validate(validCategoryDTO, response);

        Assertions.assertTrue(response.hasErrors());

        Assertions.assertTrue(
                response.getErrorCodes().contains("validation.newCategory.categoryNameExists")
        );


    }




}
