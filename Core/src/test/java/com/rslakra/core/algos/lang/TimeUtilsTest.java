package com.rslakra.core.algos.lang;

import com.rslakra.core.utils.TimeUtils;
import org.junit.Test;
import org.testng.Assert;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/2/21 10:51 AM
 */
public class TimeUtilsTest {


    /**
     * @return
     */
    private Date getLastDayOfMonth() {
        LocalDate localDate = LocalDate.parse("2019-09-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        localDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        return TimeUtils.toDate(localDate);
    }

    @Test
    public void testTimeZone() {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        System.out.println("defaultTimeZone:" + defaultTimeZone);

        TimeZone timeZoneById = TimeZone.getTimeZone("Brazil/East");
        System.out.println("timeZoneById:" + timeZoneById);

        timeZoneById = TimeZone.getTimeZone("America/Sao_Paulo");
        System.out.println("timeZoneById:" + timeZoneById);
    }


    @Test
    public void testDateString() {
        System.out.println();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        System.out.println("calendar:" + TimeUtils.toString(TimeUtils.DATE_ONLY_FORMAT, calendar));
        System.out.println("isMarkerDate:" + TimeUtils.isMarkerDate(calendar));
        System.out.println("isDateAfterEpoch:" + TimeUtils.isDateAfterEpoch(calendar));

        System.out.println();
        Calendar reset = Calendar.getInstance();
        reset.setTimeInMillis(0);
        System.out.println("reset:" + TimeUtils.toString(TimeUtils.DATE_ONLY_FORMAT, reset));
        System.out.println("isMarkerDate:" + TimeUtils.isMarkerDate(reset));
        System.out.println("isDateAfterEpoch:" + TimeUtils.isDateAfterEpoch(reset));

        System.out.println();
        Calendar epoch = Calendar.getInstance();
        Date epochDate = Date.from(Instant.parse("1970-01-01T00:00:00.00Z"));
        System.out.println("epochDate:" + TimeUtils.toString(TimeUtils.DATE_ONLY_FORMAT, epochDate));
        epoch.setTimeInMillis(epochDate.getTime());
        System.out.println("epoch:" + TimeUtils.toString(TimeUtils.DATE_ONLY_FORMAT, epoch));
        System.out.println("isMarkerDate:" + TimeUtils.isMarkerDate(epoch));
        System.out.println("isDateAfterEpoch:" + TimeUtils.isDateAfterEpoch(epoch));
    }

    /**
     * @param period
     */
    public static void logDate(Long period) {
        Date date = TimeUtils.newDate(period);
        System.out.println(date);
        String dateFormatted = TimeUtils.toString(TimeUtils.DATE_ONLY_FORMAT, date);
        System.out.println(dateFormatted);
    }

    @Test
    public void testPrintDate() {
        //
        logDate(1559347200000L);
        System.out.println();
        logDate(1467331200000L);

        System.out.println(new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
        System.out.println(new Date(System.currentTimeMillis() + 12 * 3600 * 1000));
        System.out.println(new Date(System.currentTimeMillis() + 24 * 3600 * 1000));

        System.out.println();
        String pattern = "mm-dd-yyyy";
        Date date = TimeUtils.getDateByDayOfMonth(3);
        System.out.println("date:" + date + ", " + TimeUtils.toString(pattern, date));

        pattern = "mm-dd-yyyy";
        date = TimeUtils.getDateByDayOfMonth(15);
        System.out.println("date:" + date + ", " + TimeUtils.toString(pattern, date));

        pattern = "dd-MMM-yy";
        date = TimeUtils.getDateByDayOfMonth(25);
        System.out.println("date:" + date + ", " + TimeUtils.toString(pattern, date));

        System.out.println();
        System.out.println("getDateOnlyByDayOfMonth:" + TimeUtils.getDateOnlyByDayOfMonth(5));
        System.out.println("getLastDayOfMonth:" + getLastDayOfMonth());
        System.out.println("lastDayOfMonth:" + TimeUtils.lastDayOfMonth());
        System.out.println("getNextMonthDateByDayOfMonth:" + TimeUtils.getNextMonthDateByDayOfMonth(9));
        System.out.println("getNextMonthDateByDayOfMonth:" + TimeUtils.getNextMonthDateByDayOfMonth(2));

        System.out.println();
        testTimeZone();

        System.out.println();
        testDateString();
    }

    @Test
    public void testWeekOfYear() {
        final Date dateObject = new Date(1610784000000l);
        org.junit.Assert.assertEquals(3, TimeUtils.weekOfYear(dateObject.getTime()));
        org.junit.Assert.assertEquals(3, TimeUtils.weekOfYear(dateObject));
        org.junit.Assert.assertEquals(1, TimeUtils.weekOfYear(2021, 1, 1));
        org.junit.Assert.assertEquals(2, TimeUtils.weekOfYear(2021, 1, 7));
    }

    /**
     *
     */
    @Test
    public void testCountBusinessDaysBetweenDatesJava8() {
        LocalDate d1 = LocalDate.of(2018, 8, 1);
        LocalDate d2 = LocalDate.of(2018, 8, 2);
        LocalDate d3 = LocalDate.of(2018, 8, 3);
        LocalDate d4 = LocalDate.of(2018, 8, 4);
        LocalDate d5 = LocalDate.of(2018, 8, 5);
        LocalDate d6 = LocalDate.of(2018, 8, 6);
        LocalDate d7 = LocalDate.of(2018, 8, 7);
        LocalDate d8 = LocalDate.of(2018, 8, 8);
        LocalDate d9 = LocalDate.of(2018, 8, 9);
        LocalDate d10 = LocalDate.of(2018, 8, 10);
        LocalDate d15 = LocalDate.of(2018, 8, 15);
        LocalDate dsep = LocalDate.of(2018, 9, 5);

        final Optional<List<LocalDate>> holidays = Optional.of(Arrays.asList(
            // 2020
            LocalDate.of(2020, 11, 11),
            LocalDate.of(2020, 11, 26),
            LocalDate.of(2020, 12, 25),

            // 2021
            LocalDate.of(2021, 1, 1),
            LocalDate.of(2021, 1, 18),
            LocalDate.of(2021, 2, 15),
            LocalDate.of(2021, 3, 31),
            LocalDate.of(2021, 5, 31),
            LocalDate.of(2021, 7, 5),
            LocalDate.of(2021, 9, 6),
            LocalDate.of(2021, 9, 9),
            LocalDate.of(2021, 9, 24)
        ));

        // same day : 0 days between
        Assert.assertEquals(0, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d1, holidays).size());
        Assert.assertEquals(1, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d2, holidays).size());
        Assert.assertEquals(2, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d3, holidays).size());
        // end on week-end
        Assert.assertEquals(2, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d4, holidays).size());
        Assert.assertEquals(2, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d5, holidays).size());
        // next week
        Assert.assertEquals(3, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d6, holidays).size());
        Assert.assertEquals(4, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d7, holidays).size());
        Assert.assertEquals(5, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d8, holidays).size());
        Assert.assertEquals(6, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d9, holidays).size());
        Assert.assertEquals(7, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d10, holidays).size());
        // start on saturday
        Assert.assertEquals(0, TimeUtils.countBusinessDaysBetweenDatesJava8(d4, d5, holidays).size());
        Assert.assertEquals(0, TimeUtils.countBusinessDaysBetweenDatesJava8(d4, d6, holidays).size());
        Assert.assertEquals(1, TimeUtils.countBusinessDaysBetweenDatesJava8(d4, d7, holidays).size());
        // start on sunday
        Assert.assertEquals(0, TimeUtils.countBusinessDaysBetweenDatesJava8(d5, d5, holidays).size());
        Assert.assertEquals(0, TimeUtils.countBusinessDaysBetweenDatesJava8(d5, d6, holidays).size());
        Assert.assertEquals(1, TimeUtils.countBusinessDaysBetweenDatesJava8(d5, d7, holidays).size());
        // go to next week
        Assert.assertEquals(10, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, d15, holidays).size());
        // next month
        Assert.assertEquals(25, TimeUtils.countBusinessDaysBetweenDatesJava8(d1, dsep, holidays).size());
        // start sat, go to next month
        Assert.assertEquals(22, TimeUtils.countBusinessDaysBetweenDatesJava8(d4, dsep, holidays).size());
    }

}
