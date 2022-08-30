package com.rslakra.java.jdk8;

import com.rslakra.core.TimeUtils;
import com.rslakra.jdk.jdk8.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Rohtash Lakra
 * @created 5/24/22 4:10 PM
 */
public class DateTimeTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeTest.class);

    @Test
    public void testCompareDates() {
        LOGGER.debug("testCompareDates");
        DateTime dateTime = new DateTime();
        //cStartDate
        Calendar cStartDate = Calendar.getInstance();
        cStartDate.setTimeInMillis(TimeUtils.getDateByDay(1));

        //cEndDate
        Calendar cEndDate = Calendar.getInstance();
        cEndDate.setTimeInMillis(TimeUtils.getDateByDay(5));

        //earliest
        Calendar earliest = Calendar.getInstance();
        earliest.setTimeInMillis(cStartDate.getTimeInMillis());

        String message = "AdGroup has no endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, null)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //latest
        Calendar latest = Calendar.getInstance();
        latest.setTimeInMillis(cEndDate.getTimeInMillis());

        message = "earliest has same as campaign startDate and latest as of campaign endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //earliest
        earliest = Calendar.getInstance();
        earliest.setTimeInMillis(TimeUtils.getDateByDay(2));

        message = "earliest is after campaign's startDate but latest is same as of campaign's endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //latest date
        latest = Calendar.getInstance();
        latest.setTimeInMillis(TimeUtils.getDateByDay(4));

        message = "earliest is after campaign's startDate and latest is before campaign's endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //earliest date
        earliest = Calendar.getInstance();
        earliest.setTimeInMillis(TimeUtils.getDateByDay(-1));

        message = "earliest is before campaign's startDate and latest is before campaign's endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //earliest date
        earliest = Calendar.getInstance();
        earliest.setTimeInMillis(TimeUtils.getDateByDay(1));

        //latest date
        latest = Calendar.getInstance();
        latest.setTimeInMillis(TimeUtils.getDateByDay(6));

        message =
            "earliest is same as campaign's startDate and latest is after campaign's endDate - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //earliest and latest before 1970
        earliest = Calendar.getInstance();
        earliest.setTimeInMillis(0);
        latest = Calendar.getInstance();
        latest.setTimeInMillis(0);

        message =
            "Both earliest and latest are before 1970 and campaign's startDate and endDate are valid - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }

        //cStartDate
        cStartDate = Calendar.getInstance();
        cStartDate.setTimeInMillis(TimeUtils.toMillis(2020, 03, 01));
        //cEndDate
        cEndDate = null;
        //earliest
        earliest = Calendar.getInstance();
        earliest.setTimeInMillis(TimeUtils.toMillis(2019, 11, 21));
        //latest
        latest = null;
        message = "earliest is before the campaign startDate and latest is null - ";
        if (dateTime.needToUpdateTimes(cStartDate, cEndDate, earliest, latest)) {
            LOGGER.debug(message + "Update Campaign");
        } else {
            LOGGER.debug(message + "Dont' Update Campaign");
        }
    }


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
        LOGGER.debug("defaultTimeZone:" + defaultTimeZone);

        TimeZone timeZoneById = TimeZone.getTimeZone("Brazil/East");
        LOGGER.debug("timeZoneById:" + timeZoneById);

        timeZoneById = TimeZone.getTimeZone("America/Sao_Paulo");
        LOGGER.debug("timeZoneById:" + timeZoneById);
    }

    @Test
    public void testPrintDate() {
        DateTime dateTime = new DateTime();
        //
        LOGGER.debug("{}", new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
        LOGGER.debug("{}", new Date(System.currentTimeMillis() + 12 * 3600 * 1000));
        LOGGER.debug("{}", new Date(System.currentTimeMillis() + 24 * 3600 * 1000));

        String pattern = "mm-dd-yyyy";
        Date date = TimeUtils.getDateByDayOfMonth(3);
        LOGGER.debug("date:" + date + ", " + TimeUtils.toString(pattern, date));

        pattern = "mm-dd-yyyy";
        date = TimeUtils.getDateByDayOfMonth(15);
        LOGGER.debug("date:" + date + ", " + TimeUtils.toString(pattern, date));

        pattern = "dd-MMM-yy";
        date = TimeUtils.getDateByDayOfMonth(25);
        LOGGER.debug("date:" + date + ", " + TimeUtils.toString(pattern, date));

        LOGGER.debug("getDateOnlyByDayOfMonth:" + TimeUtils.getDateOnlyByDayOfMonth(5));
        LOGGER.debug("getLastDayOfMonth:" + getLastDayOfMonth());
        LOGGER.debug("lastDayOfMonth:" + TimeUtils.lastDayOfMonth());
        LOGGER.debug("getNextMonthDateByDayOfMonth:" + TimeUtils.getNextMonthDateByDayOfMonth(9));
        LOGGER.debug("getNextMonthDateByDayOfMonth:" + TimeUtils.getNextMonthDateByDayOfMonth(2));

        testTimeZone();

        testCompareDates();
    }

}
