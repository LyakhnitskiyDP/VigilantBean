package com.ldp.vigilantBean.validator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;
import org.springframework.context.MessageSource;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Locale;

public class EntityProcessingResponseTest {

    private final Locale locale = new Locale("en", "US");

    private final MessageSource messageSource = mock(MessageSource.class);

    private EntityProcessingResponse response;

    @BeforeEach
    public void setUp() {

        when(messageSource.getMessage(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(null),
                ArgumentMatchers.eq(this.locale)))
            .thenReturn("A valid message");

        response = new EntityProcessingResponse(locale, messageSource);
    }

    @Test
    public void shouldReturnExternalizedErrorMessages() {

        response.addErrorCodes(List.of("errorCode1", "errorCode2", "errorCode3"));
        response.externalizeMessages();

        assertTrue(response.hasErrors());
        assertEquals(3, response.getErrorCodes().size());

        response.getErrorCodes()
                .forEach((errorCode) -> assertEquals("A valid message", errorCode));
    }

    @Test
    public void shouldHaveNoErrorCodesAndOneSuccessCode() {

        response.setSuccessCode("successCode");
        response.externalizeMessages();

        assertFalse(response.hasErrors());
        assertEquals(response.getSuccessCode(), "A valid message");
    }

}
