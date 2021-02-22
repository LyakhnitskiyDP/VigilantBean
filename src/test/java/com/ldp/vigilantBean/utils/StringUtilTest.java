package com.ldp.vigilantBean.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

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

    @Nested
    @DisplayName("Test method generateStringOfSize")
    public class testGenerateStringOfSize {

        @Test
        @DisplayName("Generated string should be of corresponding size")
        public void stringsShouldBeOfCorrespondingSize() {

            Map<String, Integer> generatedStrings = Map.of(
                    StringUtil.generateStringOfSize(1), 1,
                    StringUtil.generateStringOfSize(16), 16,
                    StringUtil.generateStringOfSize(128), 128,
                    StringUtil.generateStringOfSize(333),333
            );


            Assertions.assertFalse(
                generatedStrings.entrySet()
                               .stream()
                               .anyMatch(
                                       (pair) -> pair.getKey().length() != pair.getValue().intValue()
                               )
            );
        }

    }

}
