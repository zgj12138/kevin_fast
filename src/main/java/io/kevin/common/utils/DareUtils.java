package io.kevin.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期处理
 * @author ZGJ
 * @date 2017/7/3 21:47
 **/
public class DareUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 转成成yyyy-MM-dd格式
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 自定义转成格式
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if(date != null) {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
        return null;
    }
}
