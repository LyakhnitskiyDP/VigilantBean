package com.ldp.vigilantBean.utils;

import org.springframework.util.StringUtils;

public class StringUtil {


    public static String getSmallerVersion(String string) {

        String shortString = "";
        shortString = string.replace(" ", "");
        shortString = StringUtils.uncapitalize(shortString);

        return shortString;
    }

    public static String partiallyHideEmail(String email) {

        String[] emailParts = email.split("@");

        String leftPart = hidePart(emailParts[0], .65);
        String rightPart = hidePart(emailParts[1], .35);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(leftPart)
                     .append("@")
                     .append(rightPart);

        return stringBuilder.toString();
    }

    private static String hidePart(String part, double hiddenPortion) {

        int numberOfHiddenSymbols =
                ((int) Math.floor((double) part.length() * hiddenPortion));

        int numberOfUnchangedSymbols = part.length() - numberOfHiddenSymbols;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberOfUnchangedSymbols; i++)
            stringBuilder.append(part.charAt(i));

        for (int i = 0; i < numberOfHiddenSymbols; i++)
            stringBuilder.append("*");

        return stringBuilder.toString().substring(0, part.length());
    }
}
