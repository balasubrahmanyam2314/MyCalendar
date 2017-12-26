package com.balu.library.utils;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by seken on 26-12-2017.
 */

public class DateUtils {

    public static Calendar addDay(Calendar calendar) {
        calendar.add(Calendar.DATE, 1);
        Log.d("xxxx", calendar.getTime().toString());
        return calendar;
    }
}
