package cn.com.zv2.util.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lb
 * @date 2019/9/29 19:37
 */
public class DateUtils {

    public static final String FORMAT_PATTERN_HH_MM = "HH:mm";
    public static final String FORMAT_PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_ZH_PATTERN_YYYY_MM_DD = "yyyy年MM月dd日";
    public static final String FORMAT_PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_ZH_PATTERN_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日 HH:mm";
    public static final String FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_ZH_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy年MM月dd日 HH:mm:ss";

    public static String formatDate(long time) {
        return format(time, FORMAT_PATTERN_YYYY_MM_DD);
    }

    public static String formatTime(long time) {
        return format(time, FORMAT_PATTERN_HH_MM_SS);
    }

    public static String formatDateTime(long time) {
        return format(time, FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String format(long time, String pattern) {
        if (pattern == null || pattern.trim().length() == 0) {
            pattern = FORMAT_PATTERN_YYYY_MM_DD;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date(time));
    }

    public static String formatDate(Date time) {
        return format(time, FORMAT_PATTERN_YYYY_MM_DD);
    }

    public static String formatTime(Date time) {
        return format(time, FORMAT_PATTERN_HH_MM_SS);
    }

    public static String formatDateTime(Date time) {
        return format(time, FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String format(Date time, String pattern) {
        if (time == null) {
            return null;
        }
        if (pattern == null || pattern.trim().length() == 0) {
            pattern = FORMAT_PATTERN_YYYY_MM_DD;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(time);
    }

    public static Long parse(String date) {
        return parse(date, FORMAT_PATTERN_YYYY_MM_DD);
    }

    public static Long parse(String date, String pattern) {
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        if (pattern == null || pattern.trim().length() == 0) {
            pattern = FORMAT_PATTERN_YYYY_MM_DD;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date parseDate = dateFormat.parse(date);
            return parseDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
