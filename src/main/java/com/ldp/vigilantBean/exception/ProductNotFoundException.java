package com.ldp.vigilantBean.exception;

public class ProductNotFoundException extends ItemNotFoundException{

    private Long productId;

    public ProductNotFoundException(Long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

}
