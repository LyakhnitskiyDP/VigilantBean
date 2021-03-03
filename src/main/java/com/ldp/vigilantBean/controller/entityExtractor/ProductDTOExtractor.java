package com.ldp.vigilantBean.controller.entityExtractor;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Class for extracting ProductDTO object, setting main fields, categories and pictures.
 */
@Component
public class ProductDTOExtractor implements EntityExtractor<ProductDTO> {

    @Override
    public ProductDTO extractEntity(MultipartHttpServletRequest request) {

        ProductDTO productDTO = ProductDTO.builder()
                                            .name(request.getParameter("newProductName"))
                                            .description(request.getParameter("newProductDescription"))
                                            .ingredients(request.getParameter("newProductIngredients"))
                                            .quantityPerUnit(request.getParameter("newProductQuantityPerUnit").isEmpty() ? 0 : Integer.parseInt(request.getParameter("newProductQuantityPerUnit")))
                                            .unitWeight((request.getParameter("newProductUnitWeight").isEmpty() ? 0L : Long.parseLong(request.getParameter("newProductUnitWeight"))))
                                            .manufacturer(request.getParameter("newProductManufacturer"))
                                            .allergyInformation(request.getParameter("newProductAllergyInformation"))
                                            .origins(request.getParameter("newProductOrigins"))
                                            .unitsInStock(request.getParameter("newProductUnitsInStock").isEmpty() ? 0L : Long.parseLong(request.getParameter("newProductUnitsInStock")))
                                            .unitPrice(request.getParameter("newProductUnitPrice").isEmpty() ? BigDecimal.ZERO : BigDecimal.valueOf(Long.parseLong(request.getParameter("newProductUnitPrice"))))
                                          .build();

        productDTO.setCategoryNames(
                extractCategoryNames(request)
        );

        productDTO.setPrimaryPicture(
                request.getFile("newProductMainPhoto")
        );

        productDTO.setSecondaryPictures(
                request.getFiles("newProductSecondaryPhotos")
        );

        return productDTO;
    }

    private Set<String> extractCategoryNames(MultipartHttpServletRequest request) {

        Set<String> categoryNames = new HashSet<>();
        request.getParameterNames().asIterator().forEachRemaining(
                (parameterName) -> {
                    if (parameterName.startsWith("category_"))
                        categoryNames.add(request.getParameter(parameterName));
                }
        );
        return categoryNames;
    }

}
