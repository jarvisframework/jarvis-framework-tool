package com.github.jarvisframework.tool.core.date;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>日期时间处理类(从项目整理)</p>
 *
 * @author 王涛
 * @date 2019-11-21 10:42:17
 */
public class DateTimeUtils {

    /**
     * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    public static final String DEFAULT_DATETIME_PATTERN = DateTimeFormat.LONG_DATE_PATTERN_LINE.pattern;

    /**
     * Date默认时区
     */
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * 加锁安全日期对象
     */
    private static ThreadLocal<DateFormat> LOCAL = new ThreadLocal<>();

    /**
     * 秒
     */
    private static final int SECOND_MILLI = 1000;
    /**
     * 分钟
     */
    private static final long MINUTE_MILLI = 60 * SECOND_MILLI;
    /**
     * 小时
     */
    private static final long HOUR_MILLI = 60 * MINUTE_MILLI;
    /**
     * 天
     */
    private static final long DAY_MILLI = 24 * HOUR_MILLI;
    /**
     * 月
     */
    private static final long MONTH_MILLI = 31 * DAY_MILLI;
    /**
     * 年
     */
    private static final long YEAR_MILLI = 12 * DAY_MILLI;


    // ========================时间元素========================

    /**
     * 年
     */
    private static final String YEAR = "year";
    /**
     * 月
     */
    private static final String MONTH = "month";
    /**
     * 星期
     */
    private static final String WEEK = "week";
    /**
     * 天
     */
    private static final String DAY = "day";
    /**
     * 小时
     */
    private static final String HOUR = "hour";
    /**
     * 分钟
     */
    private static final String MINUTE = "minute";
    /**
     * 秒
     */
    private static final String SECOND = "second";

    // ========================星期元素========================

    /**
     * 星期一
     */
    private static final String MONDAY = "MONDAY";
    /**
     * 星期二
     */
    private static final String TUESDAY = "TUESDAY";
    /**
     * 星期三
     */
    private static final String WEDNESDAY = "WEDNESDAY";
    /**
     * 星期四
     */
    private static final String THURSDAY = "THURSDAY";
    /**
     * 星期五
     */
    private static final String FRIDAY = "FRIDAY";
    /**
     * 星期六
     */
    private static final String SATURDAY = "SATURDAY";
    /**
     * 星期日
     */
    private static final String SUNDAY = "SUNDAY";


    /**
     * 私有化构造器
     */
    private DateTimeUtils() {
    }

    // =============================common============================

    /**
     * 返回当前时间（毫秒）
     *
     * @return
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 将Date转换为LocalDateTime类型
     *
     * @param time
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date time) {
        return LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime转换为Date类型
     *
     * @param time
     * @return
     */
    public static Date toDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定年开始
     *
     * @param date
     * @return
     */
    public static String getYearBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year + "-01-01 00:00:00";
    }

    /**
     * 获取指定年结束
     *
     * @param date
     * @return
     */
    public static String getYearEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year + "-12-31 23:59:00";
    }

    /**
     * 获取指定日期开始，时间为：00:00:00
     *
     * @param time
     * @return
     */
    public static Date getDayBegin(Date time) {
        return toDate(toLocalDateTime(time).with(LocalTime.MIN));
    }

    /**
     * 获取指定日期结束，时间为：23：59:59
     *
     * @param time
     * @return
     */
    public static Date getDayEnd(Date time) {
        return toDate(toLocalDateTime(time).with(LocalTime.MAX));
    }

    /**
     * 获取两个时间的间隔天数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getIntervalDays(Date firstDate, Date secondDate) {
        return (int) (Math.abs(firstDate.getTime() - secondDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取两个时间的间隔小时数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getIntervalHours(Date firstDate, Date secondDate) {
        return (int) (Math.abs(firstDate.getTime() - secondDate.getTime()) / (1000 * 60 * 60));
    }

    /**
     * 获取两个时间的间隔秒数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getIntervalSeconds(Date firstDate, Date secondDate) {
        return (int) (Math.abs(firstDate.getTime() - secondDate.getTime()) / 1000);
    }

    /**
     * 获取两个时间的间隔毫秒数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getIntervalMilliSecond(Date firstDate, Date secondDate) {
        return (int) (Math.abs(firstDate.getTime() - secondDate.getTime()));
    }

    /**
     * 获取两个时间的间隔分钟数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getIntervalMinutes(Date firstDate, Date secondDate) {
        return (int) (Math.abs(firstDate.getTime() - secondDate.getTime()) / (1000 * 60));
    }

    /**
     * 获取日期所属季度
     *
     * @param date
     * @return
     */
    public static int getQuarter(Date date) {
        int season = -1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static Double getSeconds(long nanosecond) {
        BigDecimal seconds = new BigDecimal(nanosecond)
                .divide(new BigDecimal(1000000))
                .divide(new BigDecimal(1000));
        return seconds.doubleValue();
    }

    // =============================jdk8 time api============================

    /**
     * String 转时间
     *
     * @param timeStr
     * @return
     */
    public static LocalDateTime parseTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转时间
     *
     * @param timeStr
     * @param format  时间格式
     * @return
     */
    public static LocalDateTime parseTime(String timeStr, DateTimeFormat format) {
        return LocalDateTime.parse(timeStr, format.formatter);
    }

    /**
     * 时间转 String
     *
     * @param time
     * @return
     */
    public static String parseTime(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * 时间转 String
     *
     * @param time
     * @param dateTimeFormat 时间格式
     * @return
     */
    public static String parseTime(LocalDateTime time, DateTimeFormat dateTimeFormat) {
        return dateTimeFormat.formatter.format(time);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间
     *
     * @param dateTimeFormat 时间格式
     * @return
     */
    public static String getCurrentDateTime(DateTimeFormat dateTimeFormat) {
        return dateTimeFormat.formatter.format(LocalDateTime.now());
    }

    // =============================jdk7 time api============================

    /**
     * 时间转默认格式字符
     *
     * @param time
     * @return
     */
    public static String parseTime(Date time) {
        return parseTime(time, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 时间转格式字符
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String parseTime(Date time, String pattern) {
        DateFormat formatter = LOCAL.get();
        if (null == formatter) {
            formatter = new SimpleDateFormat(pattern);
        }
        return formatter.format(time);
    }

    public static Date _parseTime(String timeStr) {
        return parseTime(timeStr, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 字符串转时间
     *
     * @param timeStr
     * @param pattern
     * @return
     */
    public static Date parseTime(String timeStr, String pattern) {
        DateFormat formatter = LOCAL.get();
        if (null == formatter) {
            formatter = new SimpleDateFormat(pattern);
        }
        Date date;
        try {
            date = formatter.parse(timeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 当前时间加秒
     *
     * @param second
     * @return
     */
    public static Date currentTimePlusSecond(long second) {
        return new Date(System.currentTimeMillis() + (second * SECOND_MILLI));
    }

    public static String getDateTimeStrFromCurrentPlusSecond(long second) {
        Date date = currentTimePlusSecond(second);
        return parseTime(date);
    }

    /**
     * 日期格式化枚举内部类
     */
    public enum DateTimeFormat {

        /**
         * 返回分钟格式
         */
        TIME_M_PATTERN("M"),

        /**
         * 返回小时数格式
         */
        TIME_H_PATTERN("H"),

        /**
         * 返回天数格式
         */
        DATE_D_PATTERN("D"),

        /**
         * 返回时分秒
         */
        TIME_PATTERN("HH:mm:ss"),

        /**
         * 短日期格式
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),

        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),

        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),

        SHORT_DATE_PATTERN_CN("yyyy年MM月dd日"),

        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        /**
         * 长日期格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),

        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),

        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),

        LONG_DATE_PATTERN_CN("yyyy年MM月dd日 HH时mm分ss秒"),

        // LONG_DATE_PATTERN_ORACLE("YYYY-MM-DD HH24MISS"),

        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

        /**
         * 长日期时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS"),

        /**
         * 一天开始时间
         */
        DAYTIME_START("00:00:00"),

        /**
         * 一天结束时间
         */
        DAYTIME_END("23:59:59");

        /**
         * 单列时间格式对象
         */
        private transient DateTimeFormatter formatter;

        /**
         * 时间格式
         */
        private String pattern;

        DateTimeFormat(String pattern) {
            this.pattern = pattern;
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public String getPattern() {
            return this.pattern;
        }
    }

}
