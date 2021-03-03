package com.ldp.vigilantBean.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ChronoUtil {

    /**
     * Converts java.util.Date to LocalDateTime with system time Zone.
     * @param date A date to be converted to LocalDateTime.
     * @return Converted LocalDateTime date.
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {

        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDateTime();
    }

    /**
     * Converts LocalDateTime to java.util.Date with system time Zone.
     * @param localDateTime A date to be converted to java.util.Date.
     * @return Converted java.util.Date date.
     */
    public static Date convertToDate(LocalDateTime localDateTime) {

        return Date.from(
                localDateTime.atZone(ZoneId.systemDefault())
                             .toInstant()
        );
    }

}
