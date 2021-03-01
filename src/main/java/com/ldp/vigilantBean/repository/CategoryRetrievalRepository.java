package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.category.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRetrievalRepository {

   Optional<Category> getCategoryByName(String categoryName);

   List<Category> getCategoriesByNameInBatch(Collection<String> categoryNames);

   List<Category> getAllCategories();
}
