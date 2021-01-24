package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.Product;
import com.ldp.vigilantBean.repository.impl.ProductPagination;

import java.util.List;
import java.util.Optional;

public interface ProductRetrievalRepository {

    List<Product> getAllProducts(Pagination pagination);
    Long getNumberOfAllProducts();

    List<Product> getProductsByCategory(String category, Pagination pagination);
    Long getNumberOfProductsByCategory(String category);

    Optional<Product> getProductById(Long id);
}
