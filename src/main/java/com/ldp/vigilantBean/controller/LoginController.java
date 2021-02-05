package com.ldp.vigilantBean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {


    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(
            Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        if (error != null)
            model.addAttribute("errorMsg", "Your credentials are invalid");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully");

        return "login";
    }


}
