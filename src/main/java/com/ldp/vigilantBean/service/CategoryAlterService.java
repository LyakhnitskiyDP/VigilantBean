package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;

import java.util.Optional;

public interface CategoryAlterService {

    Optional<Category> addNewCategory(CategoryDTO categoryDTO);

}
