package com.ldp.vigilantBean.controller.extractor;

import com.ldp.vigilantBean.domain.product.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDTOExtractorTest {

    private ProductDTOExtractor productDTOExtractor = new ProductDTOExtractor();

    private MultipartHttpServletRequest request;

    @BeforeEach
    public void setUp() {

        request = Mockito.mock(MultipartHttpServletRequest.class);

        when(request.getParameter(ArgumentMatchers.anyString()))
                    .thenReturn("Valid Argument");

        when(request.getFile(ArgumentMatchers.anyString()))
                    .thenReturn(mock(MultipartFile.class));
        when(request.getFiles(ArgumentMatchers.anyString()))
                    .thenReturn(List.of(mock(MultipartFile.class)));

        when(request.getParameterNames())
                    .thenReturn(Collections.enumeration(List.of()));

    }

    @Test
    public void shouldExtractProductDTO() throws IllegalAccessException {

        ProductDTO productDTO = productDTOExtractor.extractEntity(request);

        Field[] productDTOFields = ProductDTO.class.getFields();
        for (Field field : productDTOFields)
            Assertions.assertNotNull(field.get(productDTO));
    }

}
