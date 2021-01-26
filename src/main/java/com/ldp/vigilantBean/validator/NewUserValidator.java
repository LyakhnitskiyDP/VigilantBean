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


    private Map<Pattern, String> regexPasswordPatterns;

    {
        regexPasswordPatterns = Map.of(Pattern.compile(".*\\d"), "must contain digits",
                                       Pattern.compile(".*[a-z]"), "must contain lower-case letters",
                                       Pattern.compile(".{8,}"), "must be at least 8 characters");

    }


    @Override
    public boolean supports(Class<?> aClass) {
        return AppUserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {



    }

    private void checkPassword(final String password,
                               final Errors errors) {

        regexPasswordPatterns.forEach(
                (pattern, description) -> {

                    if (!pattern.matcher(password).matches())
                        errors.rejectValue(password, description);
                }
        );
    }

    private void checkEmail(AppUserDTO user, Errors errors) {

        
    }

    private void checkUsername(AppUserDTO user, Errors errors) {

    }
}
