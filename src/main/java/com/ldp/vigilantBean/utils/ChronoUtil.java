package com.ldp.vigilantBean.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ChronoUtil {

    public static LocalDateTime convertToLocalDateTime(Date date) {

        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDateTime();
    }

    public static Date convertToDate(LocalDateTime localDateTime) {

        return Date.from(
                localDateTime.atZone(ZoneId.systemDefault())
                             .toInstant()
        );
    }

}