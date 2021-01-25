package com.ldp.vigilantBean.controller;

import com.ldp.vigilantBean.domain.registration.RegistrationRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class RegistrationController {

   public String register(
           @RequestBody
           RegistrationRequest registrationRequest) {


     return "hello";
   }

}
