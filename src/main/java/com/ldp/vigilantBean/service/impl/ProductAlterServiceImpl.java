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
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
public class ProductAlterServiceImpl implements ProductAlterService {

    private ProductAlterRepository productAlterRepository;

    private CategoryRetrievalRepository categoryRetrievalRepository;

    private StorageService storageService;

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

    @Override
    public Optional<Product> addNewProduct(ProductDTO productDTO) {

        Product product = extractProduct(productDTO);

        List<Category> productCategories =
                categoryRetrievalRepository.getCategoriesByNameInBatch(productDTO.getCategoryNames());

        if (productCategories.isEmpty()) {
            log.error("Supplied categories for a new Product are not found");
            return Optional.empty();
        }

        product.setCategories(new HashSet<>(productCategories));

        //Save the product for product's ID
        productAlterRepository.addNewProduct(product);

        //Create picture objects
        /*
        Picture primaryPicture = Picture.builder()
                                        .name(product.getProductId() + "_")
                                        .extension(StringUtils.getFilenameExtension(productDTO.getPrimaryPicture().getOriginalFilename()))

         */

        //Save pictures on a disc



        return Optional.empty();
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
}
