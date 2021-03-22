package com.ldp.vigilantBean.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Template Pattern implementation for organized validation of entities.
 * Process:
 * 1. Check for constraint violations (javax.validation)
 * 2. Custom validation (May use checking consistency with a DB, etc.)
 * @param <E> Entity class to validate
 */
public abstract class NewEntityValidator <E> {

    protected javax.validation.Validator beanValidator;

    public NewEntityValidator(Validator validator) {
        this.beanValidator = validator;
    }

    public final void validate(E entity, EntityProcessingResponse response) {

        checkForConstraintViolations(entity, response);

        validateConsistency(entity, response);
    }

    protected void checkForConstraintViolations
            (E entity, EntityProcessingResponse response) {

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

    protected abstract void validateConsistency(E entity, EntityProcessingResponse response);
}
