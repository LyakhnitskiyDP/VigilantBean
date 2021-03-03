package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.Picture;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import com.ldp.vigilantBean.repository.CategoryRetrievalRepository;
import com.ldp.vigilantBean.repository.ProductAlterRepository;
import com.ldp.vigilantBean.repository.impl.ProductAlterRepositoryImpl;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.service.ProductAlterService;
import com.ldp.vigilantBean.service.StorageService;
import com.ldp.vigilantBean.utils.StringUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.*;

@Service
@Log4j2
public class ProductAlterServiceImpl implements ProductAlterService {

    private ProductAlterRepository productAlterRepository;

    private CategoryRetrievalRepository categoryRetrievalRepository;

    private StorageService storageService;

    private ServletContext context;

    public ProductAlterServiceImpl(
            @Autowired
            CategoryRetrievalRepository categoryRetrievalRepository,
            @Autowired
            StorageService storageService,
            @Autowired
            ProductAlterRepository productAlterRepository) {

       this.productAlterRepository = productAlterRepository;
       this.categoryRetrievalRepository = categoryRetrievalRepository;
       this.storageService = storageService;
    }

    @Autowired
    public void setContext(ServletContext context) {
        this.context = context;
    }

    @Override
    public Optional<Product> addNewProduct(ProductDTO productDTO) {

        Product product = extractProduct(productDTO);

        List<Category> productCategories =
                categoryRetrievalRepository.getCategoriesByNameInBatch(
                        productDTO.getCategoryNames()
                );

        if (productCategories.isEmpty()) {
            log.error("Supplied categories for a new Product are not found");
            return Optional.empty();
        }

        product.setCategories(new HashSet<>(productCategories));

        //Save the product for product's ID
        productAlterRepository.addNewProduct(product);

        //Create picture objects
        Picture primaryPicture = Picture.builder()
                                            .name(product.getProductId() + "_1")
                                            .extension(StringUtils.getFilenameExtension(productDTO.getPrimaryPicture().getOriginalFilename()))
                                            .relativePath(getRelativePathToProductFolder())
                                        .build();
        storageService.store(
                productDTO.getPrimaryPicture(),
                context.getRealPath("/") + primaryPicture.getRelativePath(),
                primaryPicture.getName() + "." + primaryPicture.getExtension()
        );

        List<Picture> secondaryPictures = new ArrayList<>();

        int secondaryPictureCount = 2;
        for (MultipartFile secondaryPictureFile : productDTO.getSecondaryPictures()) {

            Picture secondaryPicture = Picture.builder()
                                                .name(product.getProductId() + "_" + secondaryPictureCount++)
                                                .extension(StringUtils.getFilenameExtension(productDTO.getPrimaryPicture().getOriginalFilename()))
                                                .relativePath(getRelativePathToProductFolder())
                                              .build();
            storageService.store(
                    secondaryPictureFile,
                    context.getRealPath("/") + secondaryPicture.getRelativePath(),
                    secondaryPicture.getName() + "." + secondaryPicture.getExtension()
            );
        }

        secondaryPictures.add(primaryPicture);
        product.setPictures(new HashSet<>(secondaryPictures));

        productAlterRepository.updateProduct(product);

        return productAlterRepository.updateProduct(product);
    }

    private Product extractProduct(ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityPerUnit(productDTO.getQuantityPerUnit());
        product.setUnitWeight(productDTO.getUnitWeight());
        product.setUnitsInStock(productDTO.getUnitsInStock());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setOrigins(productDTO.getOrigins());
        product.setManufacturer(productDTO.getManufacturer());
        product.setIngredients(productDTO.getIngredients());
        product.setAllergyInformation(productDTO.getAllergyInformation());

        return product;
    }

    private String getRelativePathToProductFolder() {

        return StringUtil.getRelativePath("resources", "images", "products");
    }


}
