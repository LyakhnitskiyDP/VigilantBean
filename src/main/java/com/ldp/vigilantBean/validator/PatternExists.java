package com.ldp.vigilantBean.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PatternExistsValidator.class)
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PatternExists {

    public String regExp();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
