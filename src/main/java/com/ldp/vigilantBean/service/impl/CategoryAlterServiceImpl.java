package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.repository.CategoryAlterRepository;
import com.ldp.vigilantBean.service.CategoryAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryAlterServiceImpl implements CategoryAlterService {

    private CategoryAlterRepository categoryAlterRepository;

    public CategoryAlterServiceImpl(
            @Autowired
            CategoryAlterRepository categoryAlterRepository) {

        this.categoryAlterRepository = categoryAlterRepository;
    }

    @Override
    public Optional<Category> addNewCategory(CategoryDTO categoryDTO) {

        Category category = extractCategory(categoryDTO);

        return categoryAlterRepository.addNewCategory(category);
    }

    private Category extractCategory(CategoryDTO categoryDTO) {

        return Category.builder()
                       .name(categoryDTO.getName())
                       .description(categoryDTO.getDescription())
                       .shortName(categoryDTO.getShortName())
                       .build();

    }
}

