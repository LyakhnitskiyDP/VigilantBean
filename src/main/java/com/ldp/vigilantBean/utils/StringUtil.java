package com.ldp.vigilantBean.utils;

public class StringUtil {

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

        System.out.println("Number of Hidden symbols: " + numberOfHiddenSymbols);
        System.out.println("Number of unchanged symbols: " + numberOfUnchangedSymbols);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberOfUnchangedSymbols; i++)
            stringBuilder.append(part.charAt(i));

        for (int i = 0; i < numberOfHiddenSymbols; i++)
            stringBuilder.append("*");

        return stringBuilder.toString().substring(0, part.length());
    }
}
