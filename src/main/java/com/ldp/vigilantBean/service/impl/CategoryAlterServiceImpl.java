package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.Picture;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.repository.CategoryAlterRepository;
import com.ldp.vigilantBean.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CategoryAlterServiceImpl implements CategoryAlterService {

    private CategoryAlterRepository categoryAlterRepository;
    private StorageService storageService;

    public CategoryAlterServiceImpl(
            @Autowired
            StorageService storageService,
            @Autowired
            CategoryAlterRepository categoryAlterRepository) {

        this.storageService = storageService;
        this.categoryAlterRepository = categoryAlterRepository;
    }

    @Override
    public Optional<Category> addNewCategory(CategoryDTO categoryDTO) {

        Category category = extractCategory(categoryDTO);

        storageService.store(
                categoryDTO.getPicture(),
                categoryDTO.getRootFilePath() + getRelativePathToPictures(),
                categoryDTO.getShortName() + "." + getPictureExtension(categoryDTO)
        );

        Picture categoryPicture = Picture.builder()
                                         .name(categoryDTO.getShortName())
                                         .extension(getPictureExtension(categoryDTO))
                                         .relativePath(getRelativePathToPictures())
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

    private String getRelativePathToPictures() {

        String delimiter = System.getProperty("file.separator");

        return delimiter + "resources" + delimiter + "images" + delimiter + "categories";
    }

    private String getPictureExtension(CategoryDTO categoryDTO) {

        return StringUtils.getFilenameExtension(
                categoryDTO.getPicture().getOriginalFilename()
        );
    }
}
