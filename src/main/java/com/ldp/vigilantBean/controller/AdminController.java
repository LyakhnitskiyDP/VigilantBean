package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log =
            LogManager.getLogger(AdminController.class.getName());

    private ProductController productController;

    private CategoryController categoryController;

    private AppUserRetrievalService appUserRetrievalService;

    public AdminController(
            @Autowired
            AppUserRetrievalService appUserRetrievalService,
            @Autowired
            ProductController productController,
            @Autowired
            CategoryController categoryController) {

        this.productController = productController;
        this.categoryController = categoryController;
        this.appUserRetrievalService = appUserRetrievalService;
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
             .addAttribute("userEmail", hideUserEmail(user));

        return "admin/admin";
    }

    @PostMapping("/addProduct")
    public ResponseEntity<EntityProcessingResponse> processNewProduct(
            MultipartHttpServletRequest request) {

       return productController.processNewProduct(request);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<EntityProcessingResponse> processNewCategory(
            MultipartHttpServletRequest request) {

        return categoryController.processNewCategory(request);
    }

    private String hideUserEmail(AppUser user) {

        return StringUtil.partiallyHideEmail(
                user.getEmail()
               );
    }

}
