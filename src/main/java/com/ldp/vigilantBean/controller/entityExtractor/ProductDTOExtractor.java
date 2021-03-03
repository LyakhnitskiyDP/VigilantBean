package com.ldp.vigilantBean.controller.entityExtractor;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static com.ldp.vigilantBean.controller.entityExtractor.ParamExtractor.*;

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
                                            .quantityPerUnit(safelyExtractInteger(request, "newProductQuantityPerUnit"))
                                            .unitWeight(safelyExtractLong(request, "newProductUnitWeight"))
                                            .manufacturer(request.getParameter("newProductManufacturer"))
                                            .allergyInformation(request.getParameter("newProductAllergyInformation"))
                                            .origins(request.getParameter("newProductOrigins"))
                                            .unitsInStock(safelyExtractLong(request, "newProductUnitsInStock"))
                                            .unitPrice(safelyExtractBigDecimal(request, "newProductUnitPrice"))
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
