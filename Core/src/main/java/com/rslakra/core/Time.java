package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @Author: rlakra
 * @Since: 9/11/19 3:45 PM
 */
public enum Time {
    INSTANCE;

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(Time.class);
    public static final String DATE_ONLY_FORMAT = "dd-MMM-yy";
    public static final String DATE_FORMAT = "dd-MMM-yy hh:mm:ss a";

    /**
     * @param period
     * @return
     */
    public static Date newDate(final Long period) {
        return new Date(period);
    }

    /**
     * Returns the date format with the pattern.
     *
     * @param pattern
     * @return
     */
    public static SimpleDateFormat newDateFormat(final String pattern) {
        return new SimpleDateFormat(Pattern.compile(pattern).pattern());
    }

    /**
     * @param pattern
     * @param date
     * @return
     */
    public static String toString(final String pattern, final Date date) {
        return newDateFormat(pattern).format(date);
    }

    /**
     * @param pattern
     * @param calendar
     * @return
     */
    public static String toString(final String pattern, final Calendar calendar) {
        return (calendar == null ? null : toString(pattern, calendar.getTime()));
    }

    /**
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * @param localDate
     * @return
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *
     * @param day
     * @return
     */
    public static Date getDateByDayOfMonth(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * @param day
     * @return
     */
    public static Date getDateOnlyByDayOfMonth(int day) {
        return toDate(toLocalDate(getDateByDayOfMonth(day)));
    }

    /**
     * @param day
     * @return
     */
    public static Date getNextMonthDateByDayOfMonth(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        return calendar.getTime();
    }

    /**
     * @return
     */
    public static Date lastDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        ValueRange range = localDate.range(ChronoField.DAY_OF_MONTH);
        LocalDate newDate = localDate.withDayOfMonth(Long.valueOf(range.getMaximum()).intValue());
        return toDate(newDate);
    }

    /**
     * Returns true if the calendar is not null and calendar time is after epoch (1970/1/1).
     *
     * @param calendar
     * @return
     */
    public static boolean isDateAfterEpoch(final Calendar calendar) {
        return (calendar != null && calendar.getTime().after(Date.from(Instant.EPOCH)));
    }

    /**
     * Returns true if the calendar date is either null or calendar's timeInMills set to be 0. The marker date is set to
     * be empty to notify the validator or merger that the date to be set is null.
     *
     * @param calendar
     * @return
     */
    public static boolean isNullOrMarkerDate(final Calendar calendar) {
        return (calendar == null || calendar.getTimeInMillis() == 0);
    }

    /**
     * Returns true if the startTime and endTime are not null and startTime is before endTime.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isStartTimeBeforeEndTime(final Calendar startTime, final Calendar endTime) {
        return (startTime != null && endTime != null && startTime.getTime().before(endTime.getTime()));
    }


    /**
     * @param day
     * @return
     */
    public static long getDateByDay(final int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime().getTime();
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long toMillis(final int year, final int month, final int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime().getTime();
    }

    /**
     * Returns the week from the given milliseconds.
     *
     * @param milliSeconds
     * @return
     */
    public static int weekOfYear(final Long milliSeconds) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Returns the week from the given milliseconds.
     *
     * @param date
     * @return
     */
    public static int weekOfYear(final Date date) {
        return weekOfYear(date.getTime());
    }

    /**
     * Returns the week from the given milliseconds.
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int weekOfYear(final int year, int month, int day) {
        // The ChronoField.ALIGNED_WEEK_OF_YEAR is not the accurate.
//        return LocalDate.of(year, month, day).get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        return LocalDate.of(year, month, day).get(WeekFields.of(Locale.getDefault()).weekOfYear());
    }

}