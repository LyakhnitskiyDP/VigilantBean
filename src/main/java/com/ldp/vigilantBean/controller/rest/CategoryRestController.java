package com.ldp.vigilantBean.controller.rest;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryRetrievalService categoryRetrievalService;

    public CategoryRestController(
            @Autowired
            CategoryRetrievalService categoryRetrievalService) {

        this.categoryRetrievalService = categoryRetrievalService;
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories() {

        return categoryRetrievalService.getAllCategories();
    }


}
