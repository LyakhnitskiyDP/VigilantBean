package com.ldp.vigilantBean.validator;


import com.ldp.vigilantBean.domain.appUser.AppUserDTO;
import com.ldp.vigilantBean.repository.AppUserRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewUserValidator implements Validator {

    private PasswordValidator regexPasswordValidator;

    private AppUserRetrievalRepository appUserRetrievalRepository;

    public NewUserValidator(
            @Autowired
            PasswordValidator regexPasswordValidator,
            @Autowired
            AppUserRetrievalRepository appUserRetrievalRepository) {

        this.appUserRetrievalRepository = appUserRetrievalRepository;

        this.regexPasswordValidator = regexPasswordValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AppUserDTO user = (AppUserDTO) target;

        regexPasswordValidator.validate(user.getPassword(), errors);

        checkEmail(user, errors);

        checkUsername(user, errors);
    }

    private void checkEmail(AppUserDTO user, Errors errors) {

        String email = user.getEmail();
        boolean isEmailPresent = appUserRetrievalRepository.getUserByEmail(email)
                                                           .isPresent();

        if (isEmailPresent)
            errors.rejectValue("email", "Email is already taken");
    }

    private void checkUsername(AppUserDTO user, Errors errors) {

        if (user.getUsername().length() < 3)
            errors.rejectValue("username", "Username is too short");
    }
}
