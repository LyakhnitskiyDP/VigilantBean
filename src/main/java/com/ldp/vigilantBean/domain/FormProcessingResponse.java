package com.ldp.vigilantBean.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class FormProcessingResponse {

    private List<String> errorCodes;

    public FormProcessingResponse(String singleCode) {

        this.errorCodes = List.of(singleCode);
    }

}
