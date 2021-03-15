package com.ldp.vigilantBean.domain.order;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Builder
@Data
public class CartItemDTO {

    private Long productId;

    @Min(value = 1, message = "validation.cart.negativeQuantity")
    @Max(value = 9999, message = "validation.cart.quantityOverflow")
    private Long quantity;

}
