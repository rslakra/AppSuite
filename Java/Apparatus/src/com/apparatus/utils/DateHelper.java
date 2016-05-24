package com.apparatus.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since May 12, 2015 10:39:10 AM
 */
public class DateHelper {
	
	private static final String DEFAULT_DATE_PATTERN = "MMM dd, yyyy hh:mm:ss a";
	private static final String DEFAULT_ONLY_DATE = "MMM dd, yyyy";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Make a new Date object. It will be initialized to the current time.
		Date now = new Date();
		
		// See what toString() returns
		System.out.println(" 1. " + now.toString());
		
		// Next, try the default DateFormat
		System.out.println(" 2. " + DateFormat.getInstance().format(now));
		
		// And the default time and date-time DateFormats
		System.out.println(" 3. " + DateFormat.getTimeInstance().format(now));
		System.out.println(" 4. " + DateFormat.getDateTimeInstance().format(now));
		
		// Next, try the short, medium and long variants of the
		// default time format
		System.out.println(" 5. " + DateFormat.getTimeInstance(DateFormat.SHORT).format(now));
		System.out.println(" 6. " + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(now));
		System.out.println(" 7. " + DateFormat.getTimeInstance(DateFormat.LONG).format(now));
		
		// For the default date-time format, the length of both the
		// date and time elements can be specified. Here are some examples:
		System.out.println(" 8. " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(now));
		System.out.println(" 9. " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now));
		System.out.println("10. " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(now));
		
		// Logs file name example:
		// Make a SimpleDateFormat for toString()'s output. This
		// has short (text) date, a space, short (text) month, a space,
		// 2-digit date, a space, hour (0-23), minute, second, a space,
		// short timezone, a final space, and a long year.
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssaa");
		String logFileNameFormat = simpleDateFormat.format(now);
		System.out.println("logFileNameFormat:" + logFileNameFormat);
		System.out.println();
		
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		String dateFormatString = formatter.format(now);
		System.out.println("dateFormatString:" + dateFormatString);
		System.out.println();
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("currentYear:" + currentYear);
		String startDateString = "Jan 1, " + currentYear + " 00:00:00 AM";
		String endDateString = "Dec 31, " + currentYear + " 23:59:59 PM";
		System.out.println("startDateString:" + startDateString);
		System.out.println("endDateString:" + endDateString);
		// String startDate = dateAsString(stringAsDate(startDateString,
		// formatter), formatter);
		// String endDate = dateAsString(stringAsDate(endDateString, formatter),
		// formatter);
		
		String startDate = getCurrentYearStartDate(DEFAULT_DATE_PATTERN);
		String endDate = getCurrentYearEndDate(DEFAULT_DATE_PATTERN);
		
		System.out.println("startDate:" + startDate);
		System.out.println("endDate:" + endDate);
		System.out.println();
		
		String pattern = "MMMMM dd, yyyy \'at\' hh:mm \'PM\'";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		long createdOnGMTMillis = 1433283208000L;
		System.out.println("createdOnGMTMillis as String:" + dateFormat.format(new Date(createdOnGMTMillis)));
		System.out.println();
		
		String dateFrom = getPreviousMonthFirstDayDate();
		System.out.println("dateFrom:" + dateFrom);
		String dateTo = getNextMonthLastDayDate();
		System.out.println("dateTo:" + dateTo);
		System.out.println();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentYearStartDate(String datePattern) {
		String startDateString = "Jan 1, " + Calendar.getInstance().get(Calendar.YEAR) + " 12:00:00 AM";
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		return dateAsString(stringAsDate(startDateString, formatter), formatter);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentYearEndDate(String datePattern) {
		String endDateString = "Dec 31, " + Calendar.getInstance().get(Calendar.YEAR) + " 11:59:59 PM";
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		return dateAsString(stringAsDate(endDateString, formatter), formatter);
	}
	
	/**
	 * Returns the date object for the specified date string in the specified
	 * dateFormat.
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date stringAsDate(String dateString, SimpleDateFormat dateFormat) {
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(dateString);
		} catch(ParseException ex) {
			ex.printStackTrace();
		}
		
		return parsedDate;
	}
	
	/**
	 * Returns the string for the specified date object in the specified
	 * dateFormat.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateAsString(Date date, SimpleDateFormat dateFormat) {
		return dateFormat.format(date);
	}
	
	/**
	 * Returns the string for the specified date object in the specified date
	 * pattern.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateAsString(Date date, String pattern) {
		return dateAsString(date, new SimpleDateFormat(pattern));
	}
	
	/**
	 * Returns the first day date string of the previous month.
	 * 
	 * @return
	 */
	private static String getPreviousMonthFirstDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String startDateString = getOnlyDate(calendar.getTime()) + " 12:00:00 AM";
		return startDateString;
	}
	
	/**
	 * Returns only date part.
	 * 
	 * @param date
	 * @return
	 */
	public static String getOnlyDate(Date date) {
		return dateAsString(date, new SimpleDateFormat(DEFAULT_ONLY_DATE));
	}
	
	/**
	 * Returns the last day date string for the next month.
	 * 
	 * @return
	 */
	private static String getNextMonthLastDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String endDateString = getOnlyDate(calendar.getTime()) + " 11:59:59 PM";
		return endDateString;
	}
}
