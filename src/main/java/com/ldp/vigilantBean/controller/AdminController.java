package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.FormProcessingResponse;
import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.category.Category;
import com.ldp.vigilantBean.domain.category.CategoryDTO;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.service.CategoryAlterService;
import com.ldp.vigilantBean.service.CategoryRetrievalService;
import com.ldp.vigilantBean.utils.StringUtil;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log =
            LogManager.getLogger(AdminController.class.getName());

    private AppUserRetrievalService appUserRetrievalService;
    private CategoryAlterService categoryAlterService;
    private CategoryRetrievalService categoryRetrievalService;
    private javax.validation.Validator validator;

    private MessageSource messageSource;


    public AdminController(
            @Autowired
            javax.validation.Validator validator,
            @Autowired
            CategoryAlterService categoryAlterService,
            @Autowired
            CategoryRetrievalService categoryRetrievalService,
            @Autowired
            AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalService = appUserRetrievalService;
        this.categoryAlterService = categoryAlterService;
        this.categoryRetrievalService = categoryRetrievalService;
        this.validator = validator;
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

    @PostMapping("/addCategory")
    public ResponseEntity<FormProcessingResponse> processNewCategory(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request) {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                                             .name(request.getParameter("newCategoryName"))
                                             .description(request.getParameter("newCategoryDescription"))
                                             .picture(file)
                                             .rootFilePath(request.getSession().getServletContext().getRealPath("/"))
                                             .build();
        categoryDTO.initShortName();

        FormProcessingResponse formResponse =
                FormProcessingResponse.builder()
                                      .locale(request.getLocale())
                                      .messageSource(this.messageSource)
                                      .build();

        Set<ConstraintViolation<CategoryDTO>> violations =
                validator.validate(categoryDTO);
        if (violations.size() > 0) {

            formResponse.setErrorCodes(
                            violations.stream()
                                      .map(ConstraintViolation::getMessage)
                                      .collect(Collectors.toList())
                         );
            formResponse.setInternationalizedErrors();

            return new ResponseEntity<>(formResponse, HttpStatus.BAD_REQUEST);
        }

        if (categoryRetrievalService.getCategoryByName(categoryDTO.getName()).isPresent()) {

           formResponse.setErrorCodes(
                           "validation.newCategory.categoryNameExists"
                   );
           formResponse.setInternationalizedErrors();

           return new ResponseEntity<>(formResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<Category> category =
                categoryAlterService.addNewCategory(categoryDTO);

        if (category.isPresent())
            return new ResponseEntity<FormProcessingResponse>(HttpStatus.OK);
        else
            return new ResponseEntity<FormProcessingResponse>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
