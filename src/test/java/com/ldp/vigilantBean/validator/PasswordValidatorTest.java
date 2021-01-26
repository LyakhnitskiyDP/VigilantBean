package com.ldp.vigilantBean.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public class PasswordValidatorTest {

    private static PasswordValidator passwordValidator;

    private static List<String> errorDescriptor =
            List.of("mustContainDigits",
                    "mustContainUpperCaseLetters",
                    "mustBe8CharactersLong");


    @BeforeAll
    public static void setUp() {

        Map<String, String> regexPasswordPatterns =
                Map.of(errorDescriptor.get(0), ".*\\d",
                        errorDescriptor.get(1), "(?=.*[A-Z]).+",
                        errorDescriptor.get(2),".{8,}");

        passwordValidator = new PasswordValidator(regexPasswordPatterns);

    }

    @ParameterizedTest
    @ValueSource(strings = {"Password123", "noTaPassword42", "anotherValidPassword421"})
    public void testValidPasswords(String validPassword) {

        Errors errors =
                new BeanPropertyBindingResult(validPassword, "validPassword");

        passwordValidator.validate(validPassword, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @ParameterizedTest
    @DisplayName("Test too short passwords")
    @ValueSource(strings = {"Short1", "", "P1"} )
    public void testInvalidShortPasswords(String invalidPassword) {

        Errors errors =
                new BeanPropertyBindingResult(invalidPassword, "invalidPassword");

        passwordValidator.validate(invalidPassword, errors);

        Assertions.assertTrue(
                errors.getAllErrors().stream()
                                     .anyMatch(error ->
                                        error.getDefaultMessage().equals(errorDescriptor.get(2))
                                     )
                             );
    }

    @ParameterizedTest
    @DisplayName("Test passwords with no uppercase letters")
    @ValueSource(strings = {"notuppercasearound123", "passworddd3", "onlylowercace"} )
    public void testInvalidPasswordsWithNoUppercaseLetter(String invalidPassword) {

        Errors errors =
                new BeanPropertyBindingResult(invalidPassword, "invalidPassword");

        passwordValidator.validate(invalidPassword, errors);

        Assertions.assertTrue(
                errors.getAllErrors().stream()
                        .anyMatch(error ->
                                error.getDefaultMessage().equals(errorDescriptor.get(1))
                        )
        );
    }

    @ParameterizedTest
    @DisplayName("Test passwords with no digits")
    @ValueSource(strings = {"NormalPassword", "password...", "Passsword"} )
    public void testInvalidPasswordsWithNoDigits(String invalidPassword) {

        Errors errors =
                new BeanPropertyBindingResult(invalidPassword, "invalidPassword");

        passwordValidator.validate(invalidPassword, errors);

        Assertions.assertTrue(
                errors.getAllErrors().stream()
                        .anyMatch(error ->
                                error.getDefaultMessage().equals(errorDescriptor.get(0))
                        )
        );
    }


}
