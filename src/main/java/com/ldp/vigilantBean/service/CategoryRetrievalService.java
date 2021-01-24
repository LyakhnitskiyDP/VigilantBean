package com.ldp.vigilantBean.service;

import com.ldp.vigilantBean.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRetrievalService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryByName(String categoryName);

}
