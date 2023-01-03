package com.anush.trendingrepositories.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static Date getDateOneDayAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getDateWeekAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        return cal.getTime();
    }

    public static Date getDateMonthAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
}
