package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.domain.registration.OnRegistrationEvent;
import com.ldp.vigilantBean.domain.registration.VerificationToken;
import com.ldp.vigilantBean.exception.InvalidVerificationToken;
import com.ldp.vigilantBean.service.AppUserRegistrationService;
import com.ldp.vigilantBean.service.VerificationTokenService;
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
import java.util.Date;
import java.util.Optional;



@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    private final NewUserValidator newUserValidator;

    private final AppUserRegistrationService registrationService;

    private final ApplicationEventPublisher eventPublisher;

    private final VerificationTokenService verificationTokenService;

    public RegistrationController(
            @Autowired
            NewUserValidator newUserValidator,
            @Autowired
            AppUserRegistrationService registrationService,
            @Autowired
            VerificationTokenService verificationTokenService,
            @Autowired
            ApplicationEventPublisher eventPublisher) {

        this.newUserValidator = newUserValidator;
        this.registrationService = registrationService;
        this.eventPublisher = eventPublisher;
        this.verificationTokenService = verificationTokenService;
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

        return "redirect:/signUp/confirmEmailPage";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {

        model.addAttribute("newUser", new AppUserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registrationConfirm")
    public String confirmRegistration(
            @RequestParam(name = "token") String token,
            Model model) {

        Optional<VerificationToken> optVerificationToken =
                verificationTokenService.get(token);

        VerificationToken verificationToken =
                optVerificationToken.orElseThrow(
                        () -> new InvalidVerificationToken(InvalidVerificationToken.Cause.NO_SUCH_TOKEN)
                );

        log.info(verificationToken.getExpiryDate());

        if (verificationToken.isExpired(new Date()))
            throw new InvalidVerificationToken(
                    InvalidVerificationToken.Cause.EXPIRED
            );

        boolean enabled =
                registrationService.enableUser(verificationToken.getAppUser());

        model.addAttribute("appUserEnabled", enabled);

        return "registrationFinish";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {

        binder.setValidator(newUserValidator);
    }

    @RequestMapping(value = "/confirmEmailPage")
    public String getEmailConfirmPage() {

        return "confirmEmailPage";
    }



}
