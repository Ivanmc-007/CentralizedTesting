package com.ivan.CT.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateParser {
    private final static String FORMAT_TIME = "HH:mm";
    private final static String FORMAT_DATE = "yyyy-MM-dd";
    private final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";

    private final static DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
    private final static DateFormat timeFormat = new SimpleDateFormat(FORMAT_TIME);
    private final static DateFormat dateTimeFormat = new SimpleDateFormat(FORMAT_DATE_TIME);

    static {
        // By default, SimpleDateFormat.setLenient() is set to true,
        // you should always turn it off to make your date validation more strictly.
        dateFormat.setLenient(false);
        timeFormat.setLenient(false);
        dateTimeFormat.setLenient(false);
    }

    public static String getJustDate(Date date) {
        return dateFormat.format(date);
    }

    public static String getJustTime(Date date) {
        return timeFormat.format(date);
    }

    public static String getDateAndTime(Date date) {
        return dateTimeFormat.format(date);
    }

    public static Date convertStringAsJustDateToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static Date convertStringAsDateTimeToDate(String dateTime) throws ParseException {
        return dateTimeFormat.parse(dateTime);
    }

    public static Date convertStringAsJustTimeToDate(String time) throws ParseException {
        return timeFormat.parse(time);
    }
}
