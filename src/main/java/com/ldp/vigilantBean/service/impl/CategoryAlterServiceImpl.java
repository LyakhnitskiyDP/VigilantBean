package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.Picture;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.repository.CategoryAlterRepository;
import com.ldp.vigilantBean.service.*;
import com.ldp.vigilantBean.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import java.util.Optional;

@Service
public class CategoryAlterServiceImpl implements CategoryAlterService {

    private CategoryAlterRepository categoryAlterRepository;
    private StorageService storageService;

    private ServletContext context;

    public CategoryAlterServiceImpl(
            @Autowired
            StorageService storageService,
            @Autowired
            CategoryAlterRepository categoryAlterRepository) {

        this.storageService = storageService;
        this.categoryAlterRepository = categoryAlterRepository;
    }

    @Autowired
    public void setContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @Override
    public Optional<Category> addNewCategory(CategoryDTO categoryDTO) {

        Category category = extractCategory(categoryDTO);

        storageService.store(
                categoryDTO.getPicture(),
                context.getRealPath("/") + getRelativePathToCategoryPictures(),
                categoryDTO.getShortName() + "." + getPictureExtension(categoryDTO)
        );

        Picture categoryPicture = Picture.builder()
                                         .name(categoryDTO.getShortName())
                                         .extension(getPictureExtension(categoryDTO))
                                         .relativePath(getRelativePathToCategoryPictures())
                                         .build();

        category.setPicture(categoryPicture);

        return categoryAlterRepository.addNewCategory(category);
    }

    private Category extractCategory(CategoryDTO categoryDTO) {

        return Category.builder()
                       .name(categoryDTO.getName())
                       .description(categoryDTO.getDescription())
                       .shortName(categoryDTO.getShortName())
                       .build();

    }

    private String getRelativePathToCategoryPictures() {

        return StringUtil.getRelativePath("resources", "images", "categories");
    }

    private String getPictureExtension(CategoryDTO categoryDTO) {

        return StringUtils.getFilenameExtension(
                categoryDTO.getPicture().getOriginalFilename()
        );
    }
}

