package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.exception.CategoryNotFoundException;
import com.ldp.vigilantBean.exception.ProductNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ItemNotFoundExceptionHandler {

    private static final Logger log =
            LogManager.getLogger(ItemNotFoundExceptionHandler.class.getName());

    @ExceptionHandler
    public ModelAndView handleProductNotFoundException(ProductNotFoundException exception) {

        log.error("Tried to access non-existing product id: " + exception.getProductId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productId", exception.getProductId());
        modelAndView.setViewName("exceptions/productNotFound");

        return modelAndView;
    }

    @ExceptionHandler
    public ModelAndView handleCategoryNotFoundException(CategoryNotFoundException exception) {

        log.error("Tried to access non-existing category with name " + exception.getCategoryName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryName", exception.getCategoryName());
        modelAndView.setViewName("exceptions/categoryNotFound");

        return modelAndView;
    }

}
