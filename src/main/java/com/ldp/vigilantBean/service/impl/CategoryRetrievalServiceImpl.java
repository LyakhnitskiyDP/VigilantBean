package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.repository.CategoryRetrievalRepository;
import com.ldp.vigilantBean.repository.impl.CategoryRetrievalRepositoryImpl;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryRetrievalServiceImpl implements CategoryRetrievalService {

    private CategoryRetrievalRepository categoryRetrievalRepository;

    public CategoryRetrievalServiceImpl(
            @Autowired
            CategoryRetrievalRepositoryImpl categoryRetrievalRepository) {

        this.categoryRetrievalRepository = categoryRetrievalRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRetrievalRepository.getAllCategories();
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRetrievalRepository.getCategoryByName(categoryName);
    }
}
