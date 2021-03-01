package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.product.Product;
import com.ldp.vigilantBean.repository.AppUserAlterRepository;
import com.ldp.vigilantBean.repository.ProductAlterRepository;
import com.ldp.vigilantBean.service.ProductAlterService;
import com.ldp.vigilantBean.validator.FormProcessingResponse;
import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.domain.product.ProductDTO;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.service.CategoryAlterService;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.utils.StringUtil;
import com.ldp.vigilantBean.validator.NewCategoryValidator;
import com.ldp.vigilantBean.validator.NewProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log =
            LogManager.getLogger(AdminController.class.getName());

    private AppUserRetrievalService appUserRetrievalService;
    private CategoryAlterService categoryAlterService;
    private CategoryRetrievalService categoryRetrievalService;
    private ProductAlterService productAlterService;

    private NewCategoryValidator newCategoryValidator;
    private NewProductValidator newProductValidator;

    private MessageSource messageSource;


    public AdminController(
            @Autowired
            CategoryAlterService categoryAlterService,
            @Autowired
            CategoryRetrievalService categoryRetrievalService,
            @Autowired
            NewProductValidator newProductValidator,
            @Autowired
            ProductAlterService productAlterService,
            @Autowired
            AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalService = appUserRetrievalService;
        this.categoryAlterService = categoryAlterService;
        this.categoryRetrievalService = categoryRetrievalService;
        this.newProductValidator = newProductValidator;
        this.productAlterService = productAlterService;
    }

    @GetMapping
    public String getAdminPage(
            Model model,
            Authentication authentication) {

        Optional<AppUser> optUser =
                appUserRetrievalService.getAppUserByEmail(authentication.getName());

        if (!optUser.isPresent()) {
            log.error("Email of an authorized user is not found");
            return "redirect:/login";
        }

        AppUser user = optUser.get();
        model.addAttribute("user", user)
             .addAttribute("userEmail",
                           StringUtil.partiallyHideEmail(
                                user.getEmail()
                        ));

        return "admin/admin";
    }

    @PostMapping("/addProduct")
    public ResponseEntity<FormProcessingResponse> processNewProduct(
            MultipartHttpServletRequest request) {

        ProductDTO productDTO =
                extractProductDTO(request);
        FormProcessingResponse response =
                initFormProcessingResponse(request.getLocale());

        response.setSuccessCode("view.admin.addProduct.success");

        newProductValidator.validate(productDTO, response);
        response.externalizeMessages();

        if (response.hasErrors())
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        Optional<Product> savedProduct =
                productAlterService.addNewProduct(productDTO);

        if (savedProduct.isPresent())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProductDTO extractProductDTO(MultipartHttpServletRequest request) {


        ProductDTO productDTO = ProductDTO.builder()
                                              .name(request.getParameter("newProductName"))
                                              .description(request.getParameter("newProductDescription"))
                                              .ingredients(request.getParameter("newProductIngredients"))
                                              .quantityPerUnit(request.getParameter("newProductQuantityPerUnit").isEmpty() ? 0 : Integer.parseInt(request.getParameter("newProductQuantityPerUnit")))
                                              .unitWeight((request.getParameter("newProductUnitWeight").isEmpty() ? 0L : Long.parseLong(request.getParameter("newProductUnitWeight"))))
                                              .manufacturer(request.getParameter("newProductManufacturer"))
                                              .allergyInformation(request.getParameter("newProductAllergyInformation"))
                                              .origins(request.getParameter("newProductOrigins"))
                                              .unitsInStock(request.getParameter("newProductUnitsInStock").isEmpty() ? 0L : Long.parseLong(request.getParameter("newProductUnitsInStock")))
                                              .unitPrice(request.getParameter("newProductUnitPrice").isEmpty() ? BigDecimal.ZERO : BigDecimal.valueOf(Long.parseLong(request.getParameter("newProductUnitPrice"))))
                                          .build();

        Set<String> categoryNames = new HashSet<>();
        request.getParameterNames().asIterator().forEachRemaining(
                (parameterName) -> {
                    if (parameterName.startsWith("category_"))
                        categoryNames.add(request.getParameter(parameterName));
                }
        );
        productDTO.setCategoryNames(categoryNames);

        productDTO.setPrimaryPicture(
                request.getFile("newProductMainPhoto")
        );

        productDTO.setSecondaryPictures(
                request.getFiles("newProductSecondaryPhotos")
        );


        return productDTO;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<FormProcessingResponse> processNewCategory(
            MultipartHttpServletRequest request) {

        CategoryDTO categoryDTO =
                extractCategoryDTO(request);

        FormProcessingResponse formResponse =
                initFormProcessingResponse(request.getLocale());

        newCategoryValidator.validate(categoryDTO, formResponse);
        formResponse.externalizeMessages();

        if (formResponse.hasErrors())
            return new ResponseEntity<>(formResponse, HttpStatus.BAD_REQUEST);

        Optional<Category> category =
                categoryAlterService.addNewCategory(categoryDTO);

        if (category.isPresent())
            return new ResponseEntity<FormProcessingResponse>(formResponse, HttpStatus.OK);
        else {
            log.error("Exception while saving validated category");
            return new ResponseEntity<FormProcessingResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private CategoryDTO extractCategoryDTO(MultipartHttpServletRequest request) {

       CategoryDTO categoryDTO = CategoryDTO.builder()
                                                .name(request.getParameter("newCategoryName"))
                                                .description(request.getParameter("newCategoryDescription"))
                                                .picture(request.getFile("categoryPhoto"))
                                                .rootFilePath(request.getSession().getServletContext().getRealPath("/"))
                                            .build();
       categoryDTO.initShortName();

       return categoryDTO;
    }

    private FormProcessingResponse initFormProcessingResponse(Locale locale) {

        FormProcessingResponse response = new FormProcessingResponse();
        response.setLocale(locale);
        response.setMessageSource(this.messageSource);

        return response;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Autowired
    public void setNewCategoryValidator(NewCategoryValidator validator) {
        this.newCategoryValidator = validator;
    }


}
