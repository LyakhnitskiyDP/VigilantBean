package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRetrievalService {

    List<Product> getAllProducts(int pageNumber);
    Long getNumberOfProducts();

    List<Product> getProductsByCategory(String category, int pageNumber);
    Long getNumberOfProductsByCategory(String category);

    List<Product> getProductsBySearchKey(String searchKey, int pageNumber);
    Long getNumberOfProductsBySearchKey(String searchKey);

    Optional<Product> getProductById(Long id);

}
