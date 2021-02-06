package com.ldp.vigilantBean.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class PatternExistsValidatorTest {

    PatternExistsValidator validator;

    PatternExists mockAnnotation;

    @BeforeEach
    public void setUp() {
        this.validator = new PatternExistsValidator();
        this.mockAnnotation = Mockito.mock(PatternExists.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1string", "str2ing", "string3", "444", "string_$##@123"})
    @DisplayName("Should validate every string because patter [0-9] is found in each of them")
    public void testValidStringsWithDigits(String field) {

        Mockito.when(mockAnnotation.regExp()).thenReturn("[0-9]");

        validator.initialize(mockAnnotation);

        Assertions.assertTrue(validator.isValid(field, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"string", "noDigits", "notASingleDigit", "_#$*#"})
    @DisplayName("Should invalidate because pattern [0-9] is not found")
    public void testInvalidStringWithoutDigits(String field) {

        Mockito.when(mockAnnotation.regExp()).thenReturn("[0-9]");

        validator.initialize(mockAnnotation);

        Assertions.assertFalse(validator.isValid(field, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"stringWithUppercaseLetter", "ABC", "&^#*$&___ABC)(*#$("})
    @DisplayName("Should validatoe becuase pattern [A-Z] is found in each string")
    public void testValidateStringWithUppercaseLetter(String field) {

        Mockito.when(mockAnnotation.regExp()).thenReturn("[A-Z]");

        validator.initialize(mockAnnotation);

        Assertions.assertTrue(validator.isValid(field, null));
    }



}
