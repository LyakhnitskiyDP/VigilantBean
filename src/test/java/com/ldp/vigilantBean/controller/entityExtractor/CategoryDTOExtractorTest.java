package com.ldp.vigilantBean.controller.entityExtractor;

import com.ldp.vigilantBean.domain.category.CategoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.lang.reflect.Field;

public class CategoryDTOExtractorTest {

    private CategoryDTOExtractor categoryDTOExtractor = new CategoryDTOExtractor();

    private MultipartHttpServletRequest request;

    @BeforeEach
    public void setUp() {

       request = Mockito.mock(MultipartHttpServletRequest.class);

       when(request.getParameter(ArgumentMatchers.anyString()))
                   .thenReturn("Valid Argument");
       when(request.getFile(ArgumentMatchers.anyString()))
                   .thenReturn(mock(MultipartFile.class));
    }

    @Test
    public void shouldExtractCategoryDTO() throws IllegalAccessException {

        CategoryDTO categoryDTO = categoryDTOExtractor.extractEntity(request);

        Field[] categoryDTOFields = CategoryDTO.class.getFields();
        for (Field field : categoryDTOFields)
            Assertions.assertNotNull(field.get(categoryDTO));
    }

}
