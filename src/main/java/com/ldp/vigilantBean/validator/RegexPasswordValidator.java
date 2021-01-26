package com.ldp.vigilantBean.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@Component
@PropertySource("classpath:regexPasswordPatterns.properties")
class RegexPasswordValidator implements PasswordValidator {

    private final Map<Pattern, String> passwordPatterns;


    public RegexPasswordValidator(
            @Value("#{${passwordPatterns}}")
            Map<String, String> regexPasswordPatterns) {

       passwordPatterns = new HashMap<>();

       regexPasswordPatterns.forEach(
               (description, pattern) -> {

                   passwordPatterns.put(Pattern.compile(pattern), description);
               }
       );

    }


    @Override
    public boolean supports(Class<?> aClass) {
        return String.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

       String password = (String) o;

       passwordPatterns.forEach(
               (pattern, description) -> {

                   if (!pattern.matcher(password).matches())
                       errors.reject("password", description);
               }
       );

    }

}
