package com.ldp.vigilantBean.validator;

import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class FormProcessingResponse {

    private List<String> errorCodes = new ArrayList<>();

    private String successCode;

    private Locale locale;

    private MessageSource messageSource;

    public FormProcessingResponse() {

    }

    public FormProcessingResponse(Locale locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public void externalizeMessages() {

        this.errorCodes = errorCodes.stream()
                                    .map(errorCode -> messageSource.getMessage(errorCode, null, locale))
                                    .collect(Collectors.toList());

        this.successCode = messageSource.getMessage(successCode, null, locale);
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void addErrorCodes(List<String> errorCodes) {

        if (this.errorCodes == null || this.errorCodes.isEmpty())
            this.errorCodes = errorCodes;
        else
            errorCodes.addAll(errorCodes);
    }

    public void addErrorCode(String errorCode) {

        if (this.errorCodes == null || this.errorCodes.isEmpty())
            this.errorCodes = List.of(errorCode);
        else
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
