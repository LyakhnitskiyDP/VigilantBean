package com.ldp.vigilantBean.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class AccountController {

    @RequestMapping("/account")
    public String getAccountPage(
            Authentication authentication) {

        if (authentication == null) return "redirect:/login";

        if ( authorityExists("ROLE_ADMIN", authentication.getAuthorities()) ) {

            return "redirect:/admin";
        }

        if ( authorityExists("ROLE_CUSTOMER", authentication.getAuthorities()) ) {

            return "redirect:/customer";
        }

        return "redirect:/login";
    }

    private boolean authorityExists(
            String authorityName,
            Collection<? extends GrantedAuthority> listOfAuthorities) {


        return listOfAuthorities.stream()
                                .anyMatch(
                                        authority -> authority.getAuthority().equals(authorityName)
                                );
    }


}
