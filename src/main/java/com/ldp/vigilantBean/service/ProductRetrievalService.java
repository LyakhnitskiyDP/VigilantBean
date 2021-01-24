package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.Product;
import com.ldp.vigilantBean.repository.Pagination;

import java.util.List;
import java.util.Optional;

public interface ProductRetrievalService {

    List<Product> getAllProducts(int pageNumber);
    Long getNumberOfProducts();

    List<Product> getProductsByCategory(String category, int pageNumber);
    Long getNumberOfProductsByCategory(String category);

    Optional<Product> getProductById(Long id);

}
