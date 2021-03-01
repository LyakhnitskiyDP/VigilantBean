package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.product.Product;

import java.util.Optional;

public interface ProductAlterRepository {

    Optional<Product> addNewProduct(Product product);

}
