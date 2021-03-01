package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;

import java.util.Optional;

public interface ProductAlterService {

    Optional<Product> addNewProduct(ProductDTO productDTO);

}
