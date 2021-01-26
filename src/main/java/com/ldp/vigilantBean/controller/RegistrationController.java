package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.appUser.AppUserDTO;
import com.ldp.vigilantBean.domain.registration.RegistrationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signUp")
public class RegistrationController {

    private static final Logger log =
            LogManager.getLogger(RegistrationController.class.getName());

    @PostMapping
    public void register(
            @RequestBody
            RegistrationRequest registrationRequest) {

       log.info("Got a registration request: " + registrationRequest.getEmail());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {

        model.addAttribute("newUser", new AppUserDTO());
        return "registration";
    }

}
