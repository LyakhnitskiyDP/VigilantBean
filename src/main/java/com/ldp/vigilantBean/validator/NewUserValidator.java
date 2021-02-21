package com.ldp.vigilantBean.validator;


import com.ldp.vigilantBean.domain.registration.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class NewUserValidator implements Validator {

    private AppUserRetrievalRepository appUserRetrievalRepository;

    private javax.validation.Validator beanValidator;

    public NewUserValidator(
            @Autowired
            AppUserRetrievalRepository appUserRetrievalRepository,
            @Autowired
            @Qualifier("beanValidator")
            javax.validation.Validator validator) {

        this.appUserRetrievalRepository = appUserRetrievalRepository;
        this.beanValidator = validator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Set<ConstraintViolation<Object>> constraintViolations =
                beanValidator.validate(target);

        for (ConstraintViolation<Object> violation : constraintViolations) {

            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();

            errors.rejectValue(propertyPath, message);
        }

        AppUserDTO user = (AppUserDTO) target;


        checkEmail(user, errors);
    }

    private void checkEmail(AppUserDTO user, Errors errors) {

        String email = user.getEmail();
        boolean isEmailPresent = appUserRetrievalRepository.getUserByEmail(email)
                                                           .isPresent();

        if (isEmailPresent)
            errors.rejectValue("email", "validation.newUser.email.isInUse");
    }

}
