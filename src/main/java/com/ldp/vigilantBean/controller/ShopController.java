package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.Category;
import com.ldp.vigilantBean.domain.Product;
import com.ldp.vigilantBean.exception.CategoryNotFoundException;
import com.ldp.vigilantBean.exception.ProductNotFoundException;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.service.ProductRetrievalService;
import com.ldp.vigilantBean.utils.ProductsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/shop")
@PropertySource("classpath:webappConfig.properties")
public class ShopController {

    private static final Logger log =
            LogManager.getLogger(ShopController.class);

    @Autowired
    private ProductRetrievalService productRetrievalService;

    @Autowired
    private CategoryRetrievalService categoryRetrievalService;

    @Value("${interface.numberOfProductsPerPage}")
    private Integer numberOfProductsPerPage;

    @RequestMapping
    public String shop(@RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
                       Model model) {

        initHeaderModel(model);

        List<Product> products = productRetrievalService.getAllProducts(pageNumber);
        int numberOfAllProducts = productRetrievalService.getNumberOfProducts().intValue();
        model.addAttribute("products", products);

        initCategoriesModel(model);
        initPageListModel(model, numberOfAllProducts);

        return "shop";
    }

    @RequestMapping("/{category}")
    public String getByCategory(@PathVariable(value = "category") String categoryName,
                                @RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
                                Model model) {

        initHeaderModel(model, categoryName);

        List<Product> products =
                productRetrievalService.getProductsByCategory(categoryName, pageNumber);
        int numberOfProducts = productRetrievalService.getNumberOfProductsByCategory(categoryName).intValue();
        model.addAttribute("products", products);

        initCategoriesModel(model);
        initPageListModel(model, numberOfProducts);

        return "shop";
    }

    @RequestMapping("/product")
    public String product(@RequestParam(value = "productId") Long productId,
                          Model model) {

        Optional<Product> optProduct =
                productRetrievalService.getProductById(productId);

        model.addAttribute("product",
                optProduct.orElseThrow(() -> new ProductNotFoundException(productId)));

       return "product";
    }

    private void initHeaderModel(Model model, String categoryName) {

        Optional<Category> optCategory =
                categoryRetrievalService.getCategoryByName(categoryName);

        Category category =
                optCategory.orElseThrow(() -> new CategoryNotFoundException(categoryName));

        model.addAttribute("categoryName", category.getName());
        model.addAttribute("categoryImage", category.getPicture().getFullName());
    }

    private void initHeaderModel(Model model) {

        model.addAttribute("categoryName", "All Products");
        model.addAttribute("categoryImage", "allProducts.jpg");

    }

    private void initCategoriesModel(Model model) {

        List<Category> categories =
                categoryRetrievalService.getAllCategories();

        model.addAttribute("categories", categories);
    }

    private void initPageListModel(Model model, int numberOfProducts) {

        log.error("Number of Products: " + numberOfProducts);
        log.error("Number of Products per page: " + numberOfProductsPerPage);

        List<Integer> pageList =
                ProductsUtil.getPageList(numberOfProducts, numberOfProductsPerPage);

        model.addAttribute("pageList", pageList);
    }

}
