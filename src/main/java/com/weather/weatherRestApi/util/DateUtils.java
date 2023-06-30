package com.weather.weatherRestApi.util;

import com.weather.weatherRestApi.exception.CustomNotFoundException;
import com.weather.weatherRestApi.exception.ResourceNotFoundException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils() {}

    public static Date parse(String rawDate) {

        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            return sdf.parse(rawDate);
        } catch (ParseException e) {
            throw new ResourceNotFoundException("error parsing date", e);
        }
    }

    public static String format(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
}
