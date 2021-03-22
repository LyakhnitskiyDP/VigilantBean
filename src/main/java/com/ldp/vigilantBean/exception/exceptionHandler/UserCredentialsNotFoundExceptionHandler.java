package com.ldp.vigilantBean.exception.exceptionHandler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserCredentialsNotFoundExceptionHandler {

    @ExceptionHandler
    public String handleNonAuthenticatedUser(AuthenticationCredentialsNotFoundException e) {

        return "/login";
    }

}
