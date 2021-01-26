package com.ldp.vigilantBean.validator;


import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.appUser.AppUserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class NewUserValidator implements Validator {

    private PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {



    }

    private void checkPassword(final String password,
                               final Errors errors) {

    }

    private void checkEmail(AppUserDTO user, Errors errors) {


    }

    private void checkUsername(AppUserDTO user, Errors errors) {

    }
}
