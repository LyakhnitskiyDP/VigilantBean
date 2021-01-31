package com.ldp.vigilantBean.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to create new user entry")
public class InternalRegistrationException extends RuntimeException{
}
