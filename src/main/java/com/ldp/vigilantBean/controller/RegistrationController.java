package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.service.AppUserRegistrationService;
import com.ldp.vigilantBean.validator.NewUserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    private NewUserValidator newUserValidator;

    private AppUserRegistrationService registrationService;

    public RegistrationController(
            @Autowired
            NewUserValidator newUserValidator,
            @Autowired
            AppUserRegistrationService registrationService) {

        this.newUserValidator = newUserValidator;
        this.registrationService = registrationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(
            @ModelAttribute("newUser")
            @Valid
            AppUserDTO newUser,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }


        registrationService.registerUser(newUser);

        model.addAttribute("newUser", null);
        return "redirect:/signUp/confirmEmail";
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

    @RequestMapping(value = "/confirmEmail")
    public String getEmailConfirmPage() {

        return "confirmEmailPage";
    }




}
