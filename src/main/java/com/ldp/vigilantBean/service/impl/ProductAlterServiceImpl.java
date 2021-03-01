package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import com.ldp.vigilantBean.repository.CategoryRetrievalRepository;
import com.ldp.vigilantBean.repository.ProductAlterRepository;
import com.ldp.vigilantBean.repository.impl.ProductAlterRepositoryImpl;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.service.ProductAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductAlterServiceImpl implements ProductAlterService {

    private ProductAlterRepository productAlterRepository;

    private CategoryRetrievalRepository categoryRetrievalRepository;

    public ProductAlterServiceImpl(
            @Autowired
            CategoryRetrievalRepository categoryRetrievalRepository,
            @Autowired
            ProductAlterRepository productAlterRepository) {

       this.productAlterRepository = productAlterRepository;
       this.categoryRetrievalRepository = categoryRetrievalRepository;
    }

    @Override
    public Optional<Product> addNewProduct(ProductDTO productDTO) {

        Product product = extractProduct(productDTO);

        List<Category> productCategories =
                categoryRetrievalRepository.getCategoriesByNameInBatch(productDTO.getCategoryNames());

        if (productCategories.isEmpty())
            return Optional.empty();

        product.setCategories(new HashSet<>(productCategories));

        //Save the product for ID
        productAlterRepository.addNewProduct(product);

        //Create picture objects

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
