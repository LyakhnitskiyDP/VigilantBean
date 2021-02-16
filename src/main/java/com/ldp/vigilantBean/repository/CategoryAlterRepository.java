package com.ldp.vigilantBean.repository;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;

import java.util.Optional;

public interface CategoryAlterRepository {

    Optional<Category> addNewCategory(Category category);

}
