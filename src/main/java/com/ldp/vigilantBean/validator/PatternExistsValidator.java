package com.ldp.vigilantBean.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PatternExistsValidator
    implements ConstraintValidator<PatternExists, String> {

    private String regExp;

    @Override
    public boolean isValid(String s,
                           ConstraintValidatorContext constraintValidatorContext) {

        return Pattern.compile(regExp).matcher(s).find();
    }

    @Override
    public void initialize(PatternExists constraintAnnotation) {

        regExp = constraintAnnotation.regExp();
    }
}
