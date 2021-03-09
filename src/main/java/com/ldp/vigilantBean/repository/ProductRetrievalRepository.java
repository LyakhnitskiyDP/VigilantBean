package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRetrievalRepository {

    List<Product> getAllProducts(Pagination pagination);
    Long getNumberOfAllProducts();

    List<Product> getProductsByCategory(String category, Pagination pagination);
    Long getNumberOfProductsByCategory(String category);

    List<Product> getProductsBySearchKey(String searchKey, Pagination pagination);
    Long getNumberOfProductsBySearchKey(String searchKey);

    Optional<Product> getProductById(Long id);
}
