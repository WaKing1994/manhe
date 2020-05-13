package com.manhe.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

    public static Date getTime(Integer day, Integer hour, Integer minute, Integer second) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date time = calendar.getTime();
        return time;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static String dateStringFormat(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthStr = month < 10 ? "0" + month : month.toString();
        String dayStr = day < 10 ? "0" + day : day.toString();
        return year + "-" + monthStr + "-" + dayStr;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static String dateStringFormat2(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    //计算时间差，以小时为单位。如：2018-08-08 和 2018-08-07 相差24h
    public static double getDistanceTime2(Date startTime, Date endTime) {
        double hour = 0;
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();

        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        hour = (diff / (60 * 60 * 1000));
        return hour;
    }

    //计算时间差，以天数为单位。如：2018-08-08 和 2018-08-05 相差3天
    public static Integer getDistanceTime(Date startTime, Date endTime) {
        Integer days = 0;
        Long time1 = startTime.getTime();
        Long time2 = endTime.getTime();

        Long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = (int) (diff / (24 * 60 * 60 * 1000));
        return days;
    }
}
