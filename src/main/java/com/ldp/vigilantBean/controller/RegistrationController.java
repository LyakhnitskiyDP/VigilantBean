package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.validator.NewUserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    private NewUserValidator newUserValidator;

    public RegistrationController(
            @Autowired
            NewUserValidator newUserValidator) {

        this.newUserValidator = newUserValidator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(
            @ModelAttribute("newUser")
            @Valid
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
    public void initializeBinder(WebDataBinder binder) {

        binder.setValidator(newUserValidator);
    }




}
