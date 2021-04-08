package com.ldp.vigilantBean.controller.rest;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.service.AppUserRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AppUserRestController {

    private AppUserRetrievalService appUserRetrievalService;

    public AppUserRestController(
            @Autowired
            AppUserRetrievalService appUserRetrievalService) {

        this.appUserRetrievalService = appUserRetrievalService;
    }


    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated() {

        try {
            appUserRetrievalService.getAppUserDetailsOutOfContext();
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (AuthenticationCredentialsNotFoundException authenticationException) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
    }

}
