package com.walker.fetchplan.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parseDate(String date, String format) {
        if (date == null || "".trim().equals(date))
            return null;
        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getCurrentDate() {
        return new Date();
    }
}
