package com.ldp.vigilantBean.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class NewEntityValidator <E> {

    protected javax.validation.Validator beanValidator;

    public abstract void validate(E entity, FormProcessingResponse response);

    protected void checkForConstraintViolations
            (E entity, FormProcessingResponse response) {

        Set<ConstraintViolation<E>> violations =
                beanValidator.validate(entity);

        if (violations.size() > 0) {

            response.addErrorCodes(
                    violations.stream()
                              .map(ConstraintViolation::getMessage)
                              .collect(Collectors.toList())
            );
        }
    }

    protected abstract void setBeanValidator(javax.validation.Validator validator);

}
