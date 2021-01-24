package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRetrievalRepository {

   Optional<Category> getCategoryByName(String categoryName);

   List<Category> getAllCategories();

}
