package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.repository.Pagination;
import com.ldp.vigilantBean.repository.ProductRetrievalRepository;
import com.ldp.vigilantBean.repository.impl.ProductPagination;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import com.ldp.vigilantBean.utils.PaginationUtil;
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
class ProductRetrievalServiceImpl implements ProductRetrievalService {

    private static final Logger log =
            LogManager.getLogger(ProductRetrievalServiceImpl.class.getName());

    @Value("${interface.numberOfProductsPerPage}")
    private Integer numberOfProductsPerPage;

    private ProductRetrievalRepository productRetrievalRepository;

    public ProductRetrievalServiceImpl(
            @Autowired
            ProductRetrievalRepository productRetrievalRepository) {

        this.productRetrievalRepository = productRetrievalRepository;
    }

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
    public List<Product> getProductsBySearchKey(String searchKey, int pageNumber) {

        Pagination pagination = createPagination(pageNumber);

        return productRetrievalRepository.getProductsBySearchKey(
                searchKey.toLowerCase(),
                pagination
        );
    }

    @Override
    public Long getNumberOfProductsBySearchKey(String searchKey) {
        return productRetrievalRepository.getNumberOfProductsBySearchKey(
                searchKey.toLowerCase()
        );
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRetrievalRepository.getProductById(id);
    }

    Pagination createPagination(int pageNumber) {

        int firstProductIndexOnPage =
                PaginationUtil.getFirstProductIndex(pageNumber, numberOfProductsPerPage);

        Pagination pagination =
                new ProductPagination(firstProductIndexOnPage, numberOfProductsPerPage);

        return pagination;
    }

}
