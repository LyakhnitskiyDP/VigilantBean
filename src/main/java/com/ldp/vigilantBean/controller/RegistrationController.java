package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.domain.registration.OnRegistrationEvent;
import com.ldp.vigilantBean.service.AppUserRegistrationService;
import com.ldp.vigilantBean.validator.NewUserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;


@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    private final NewUserValidator newUserValidator;

    private final AppUserRegistrationService registrationService;

    private final ApplicationEventPublisher eventPublisher;

    public RegistrationController(
            @Autowired
            NewUserValidator newUserValidator,
            @Autowired
            AppUserRegistrationService registrationService,
            @Autowired
            ApplicationEventPublisher eventPublisher) {

        this.newUserValidator = newUserValidator;
        this.registrationService = registrationService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(
            @ModelAttribute("newUser")
            @Valid
            AppUserDTO newUser,
            BindingResult bindingResult,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<AppUser> optUser =
                registrationService.registerUser(newUser);

        //TODO: add more suitable exception
        if (!optUser.isPresent())
            throw new RuntimeException("Something went wrong");

        ApplicationEvent registrationEvent =
                new OnRegistrationEvent(
                        optUser.get(),
                        request.getLocale(),
                        request.getContextPath()
                );

        eventPublisher.publishEvent(registrationEvent);

        return "redirect:/signUp/finish";
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

    @RequestMapping(value = "/finish")
    public String getEmailConfirmPage() {

        return "confirmEmailPage";
    }



}
