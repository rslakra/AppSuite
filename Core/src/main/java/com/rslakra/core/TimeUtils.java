package com.rslakra.core;

import com.rslakra.core.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra
 * @created 8/2/21 10:38 AM
 */
public enum TimeUtils {
    INSTANCE;

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);
    public static final String DATE_ONLY_FORMAT = "dd-MMM-yy";
    public static final String DATE_FORMAT = "dd-MMM-yy hh:mm:ss a";
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

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
    public static String toString(final TimeZone timeZone, final String pattern, final Date date) {
        final SimpleDateFormat dateFormat = newDateFormat(pattern);
        if (BeanUtils.isNotNull(timeZone)) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat.format(date);
    }

    /**
     * @param pattern
     * @param date
     * @return
     */
    public static String toString(final String pattern, final Date date) {
        return toString(UTC_TIME_ZONE, pattern, date);
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
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * @param localDate
     * @return
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param localDateTime
     * @return
     */
    public static Date toDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param day
     * @return
     */
    public static Date getDateByDayOfMonth(final int day) {
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
    public static boolean isMarkerDate(final Calendar calendar) {
        return (calendar == null || calendar.getTimeInMillis() == 0);
    }

    /**
     * Returns true if the startTime and endTime are not null and startTime is before endTime.
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isValidateDates(final Calendar startTime, final Calendar endTime) {
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
// return LocalDate.of(year, month, day).get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        return LocalDate.of(year, month, day).get(WeekFields.of(Locale.getDefault()).weekOfYear());
    }

    /**
     * Counts business working days.
     * <p>
     * Counts the number of business days between two <code>startDate</code> and <code>endDate</code> dates. The
     * business days are considered all weekdays, excluding all holidays falling on weekdays.
     * <p>
     * The method takes an optional holiday list and uses predicates to check if a day is weekend or holiday.
     *
     * @param startDate
     * @param endDate
     * @param holidays
     * @return
     */
    public static List<LocalDate> countBusinessDaysBetweenDatesJava8(final LocalDate startDate, final LocalDate endDate,
                                                                     final Optional<List<LocalDate>> holidays) {
        LOGGER.debug("+countBusinessDaysBetweenDatesJava8({}, {}, {})", startDate, endDate, holidays);
        // Validate method arguments
        if (startDate == null || endDate == null) {
            throw new InvalidRequestException("Invalid Dates! startDate=%s, endDate=%s", startDate, endDate);
        }

        // Predicate 1: Is a given date is a holiday
        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() && holidays.get().contains(date);
        LOGGER.debug("isHoliday: {}", isHoliday);

        // Predicate 2: Is a given date is a weekday
        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        LOGGER.debug("isWeekend: {}", isWeekend);

        // Get all days between two dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        LOGGER.debug("daysBetween: {}", daysBetween);

        List<LocalDate> localDatesBetween = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween + 1)
                .collect(Collectors.toList());
        LOGGER.debug("localDatesBetween: {}", localDatesBetween);

        // Iterate over stream of all dates and check each day against any weekday or holiday
        List<LocalDate> localDates = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween + 1)
                .filter(isHoliday.or(isWeekend).negate())
                .collect(Collectors.toList());
        LOGGER.debug("-countBusinessDaysBetweenDatesJava8(), localDates: {}", localDates);
        return localDates;
    }

// private static List<LocalDate> countBusinessDaysBetweenDatesJava9(final LocalDate startDate,
//                                                                      final LocalDate endDate,
//                                                                      final Optional<List<LocalDate>> holidays) {
// // Validate method arguments
// if (startDate == null || endDate == null) {
//            throw new IllegalArgumentException(
//                "Invalid method argument(s) to countBusinessDaysBetween (" + startDate + "," + endDate + "," + holidays
//                + ")");
// }
//
// // Predicate 1: Is a given date is a holiday
// Predicate<LocalDate> isHoliday = date -> holidays.isPresent()
//                                                 && holidays.get().contains(date);
//
// // Predicate 2: Is a given date is a weekday
// Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
//                                                 || date.getDayOfWeek() == DayOfWeek.SUNDAY;
//
// // Iterate over stream of all dates and check each day against any weekday or
// // holiday
// List<LocalDate> businessDays = startDate.datesUntil(endDate)
//            .filter(isWeekend.or(isHoliday).negate())
//            .collect(Collectors.toList());
//
// return businessDays;
// }

}
