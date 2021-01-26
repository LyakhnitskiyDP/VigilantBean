package com.ldp.vigilantBean.validator;

import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;


    @BeforeAll
    public void setUp() {

        Map<String, String> regexPasswordPatterns =
                Map.of(".*\\d", "mustContainDigits");

        passwordValidator = new PasswordValidator(regexPasswordPatterns);
    }

    

}
