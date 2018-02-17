package com.rslakra.jdk8.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/20/2017 03:24:10 PM
 */
public class LocalDateTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Enter 2 Dates in (dd mm yyyy) format.");
		Scanner in = new Scanner(System.in);
		int dayAcutal = in.nextInt();
		int monthActual = in.nextInt();
		int yearActual = in.nextInt();
		int dayExpected = in.nextInt();
		int monthExpected = in.nextInt();
		int yearExpected = in.nextInt();
		in.close();

		try {
			LocalDate dateReturned = LocalDate.of(yearActual, monthActual, dayAcutal);
			LocalDate dateExpected = LocalDate.of(yearExpected, monthExpected, dayExpected);
			Period period = Period.between(dateExpected, dateReturned);
			System.out.println("period:" + period);
			System.out.println(
					"Years:" + period.getYears() + ", Months:" + period.getMonths() + ", Days:" + period.getDays());
			int allDays = (int) ChronoUnit.DAYS.between(dateExpected, dateReturned);
			System.out.println("Days Total:" + allDays);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
