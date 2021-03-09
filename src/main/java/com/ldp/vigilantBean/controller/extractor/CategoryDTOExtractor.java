package com.ldp.vigilantBean.controller.extractor;

import com.ldp.vigilantBean.domain.category.CategoryDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * Class for extracting CategoryDTO object.
 */
@Component
public class CategoryDTOExtractor implements EntityExtractor<CategoryDTO> {

    @Override
    public CategoryDTO extractEntity(MultipartHttpServletRequest request) {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(request.getParameter("newCategoryName"))
                .description(request.getParameter("newCategoryDescription"))
                .picture(request.getFile("categoryPhoto"))
                .build();
        categoryDTO.initShortName();

        return categoryDTO;
    }

}
