package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    private AppUserRetrievalRepository appUserRetrievalRepository;

    public CustomerController(
        @Autowired
        AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalRepository = appUserRetrievalRepository;
    }

    @RequestMapping("/customer")
    public String getCustomerPage(
            Authentication authentication) {

        AppUser user = appUserRetrievalRepository

        return "customer/customer";
    }

}
