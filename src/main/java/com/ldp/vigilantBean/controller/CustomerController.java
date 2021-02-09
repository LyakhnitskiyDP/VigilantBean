package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import com.ldp.vigilantBean.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class CustomerController {

    private static final Logger log =
            LogManager.getLogger(CustomerController.class.getName());

    private AppUserRetrievalService appUserRetrievalService;

    public CustomerController(
        @Autowired
        AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalService = appUserRetrievalService;
    }

    @RequestMapping("/customer")
    public String getCustomerPage(
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


        return "customer/customer";
    }

}
