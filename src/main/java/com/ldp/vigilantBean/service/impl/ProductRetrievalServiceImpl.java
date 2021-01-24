package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.Product;
import com.ldp.vigilantBean.repository.Pagination;
import com.ldp.vigilantBean.repository.ProductRetrievalRepository;
import com.ldp.vigilantBean.repository.impl.ProductPagination;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import com.ldp.vigilantBean.utils.ProductsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:webappConfig.properties")
public class ProductRetrievalServiceImpl implements ProductRetrievalService {

    private static final Logger log =
            LogManager.getLogger(ProductRetrievalServiceImpl.class.getName());

    @Value("${interface.numberOfProductsPerPage}")
    private Integer numberOfProductsPerPage;

    @Autowired
    private ProductRetrievalRepository productRetrievalRepository;

    @Override
    public List<Product> getAllProducts(int pageNumber) {

        Pagination pagination = createPagination(pageNumber);

        return productRetrievalRepository.getAllProducts(pagination);
    }

    @Override
    public Long getNumberOfProducts() {
        return productRetrievalRepository.getNumberOfAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category, int pageNumber) {


        Pagination pagination = createPagination(pageNumber);

        return productRetrievalRepository.getProductsByCategory(category, pagination);
    }

    @Override
    public Long getNumberOfProductsByCategory(String category) {
        return productRetrievalRepository.getNumberOfProductsByCategory(category);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRetrievalRepository.getProductById(id);
    }

    private Pagination createPagination(int pageNumber) {

        int firstProductIndexOnPage =
                ProductsUtil.getFirstProductIndex(pageNumber, numberOfProductsPerPage);

        Pagination pagination =
                new ProductPagination(firstProductIndexOnPage, numberOfProductsPerPage);

        return pagination;
    }

}
