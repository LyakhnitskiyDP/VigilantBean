package com.ldp.vigilantBean.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Nested
    @DisplayName("Test method partiallyHideEmail")
    public class testPartiallyHideEmail {

        @Test
        @DisplayName("Should partially hide email address")
        public void shouldPartiallyHideEmailAddress() {

            String emailAddress = "myEmailAddress@example.com";

            String hiddenEmail = StringUtil.partiallyHideEmail(emailAddress);

            Assertions.assertEquals("myEma*********@example.***", hiddenEmail);
        }

    }

}
