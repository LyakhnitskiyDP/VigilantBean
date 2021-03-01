package com.ldp.vigilantBean.domain.product;

import com.ldp.vigilantBean.domain.Picture;
import com.ldp.vigilantBean.domain.category.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class ProductDTO {

    @Size(min = 2, max = 32,
          message = "validation.newProduct.nameOutOfBoundaries")
    private String name;

    @Size(min = 10, max = 500,
          message = "validation.newProduct.descriptionOutOfBoundaries")
    private String description;

    @Positive(message = "validation.newProduct.quantityPerUnitIsNegative")
    private int quantityPerUnit;

    @Positive(message = "validation.newProduct.unitWeightIsNegative")
    private long unitWeight;

    private List<MultipartFile> secondaryPictures;

    @NotNull(message = "validation.newProduct.noPicturesAreSelected")
    private MultipartFile primaryPicture;

    @Positive(message = "validation.newProduct.unitsInStockNegative")
    private long unitsInStock;

    @DecimalMin(value = "0.0",
                inclusive = false,
                message = "validation.newProduct.negativeUnitPrice")
    @Digits(integer = 6,
            fraction = 2,
            message = "validation.newProduct.invalidUnitPriceFormat")
    private BigDecimal unitPrice;

    @Size(min = 5, max = 150,
          message = "validation.newProduct.originsOutOfBoundaries")
    private String origins;

    @Size(min = 3, max = 150,
          message = "validation.newProduct.manufacturerOutOfBoundaries")
    private String manufacturer;

    @Size(min = 10, max = 300,
          message = "validation.newProduct.ingredientsOutOfBoundaries")
    private String ingredients;

    @Size(min = 4, max = 300,
          message = "validation.newProduct.allergyInformationOutOfBoundaries")
    private String allergyInformation;

    @NotEmpty(message = "validation.newProduct.noCategoriesIsSelected")
    private Set<String> categoryNames;

}
