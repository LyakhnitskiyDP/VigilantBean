package com.ldp.vigilantBean.domain;

import lombok.*;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class FormProcessingResponse {

    private List<String> errorCodes;
    private Locale locale;
    private MessageSource messageSource;

    public void setInternationalizedErrors() {

        this.errorCodes = errorCodes.stream()
                                    .map(errorCode -> messageSource.getMessage(errorCode, null, locale))
                                    .collect(Collectors.toList());
    }

    public void setErrorCodes(String errorCode) {

        this.errorCodes = List.of(errorCode);
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }



}
