package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUserDTO;
import com.ldp.vigilantBean.validator.NewUserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    private NewUserValidator userValidator;

    public RegistrationController(
            @Autowired
            NewUserValidator newUserValidator) {

        this.userValidator = newUserValidator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(
            @ModelAttribute("newUser")
            @Validated
            AppUserDTO newUser,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        log.info("No errors found");

        return "registration";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {

        model.addAttribute("newUser", new AppUserDTO());
        return "registration";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        binder.setValidator(userValidator);
    }

}
