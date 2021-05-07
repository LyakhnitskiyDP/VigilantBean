package com.ldp.vigilantBean.validator;

import org.springframework.context.MessageSource;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class wrapping a response of entity processing.
 */
public class EntityProcessingResponse {

    private List<String> errorCodes = new ArrayList<>();
    private String successCode;

    private Locale locale;
    private MessageSource messageSource;

    public EntityProcessingResponse(Locale locale,
                                    MessageSource messageSource) {

        this.locale = locale;
        this.messageSource = messageSource;
    }

    public void externalizeMessages() {

        this.errorCodes = errorCodes.stream()
                                    .map(errorCode -> messageSource.getMessage(errorCode, null, locale))
                                    .collect(Collectors.toList());

        if (this.successCode != null)
            this.successCode = messageSource.getMessage(successCode, null, locale);
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void addErrorCodes(List<String> errorCodes) {

        if (this.errorCodes.isEmpty())
            this.errorCodes = errorCodes;
        else
            this.errorCodes.addAll(errorCodes);
    }

    public void addErrorCode(String errorCode) {
        errorCodes.add(errorCode);
    }


    public boolean hasErrors() {
        return !errorCodes.isEmpty();
    }


    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }
}
