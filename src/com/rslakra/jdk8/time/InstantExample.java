/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.jdk8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;

import com.rslakra.jdk8.Person;
import com.rslakra.jdk8.PersonFactory;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-10 09:59:52 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class InstantExample {

	/**
	 * @param args
	 */

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		Instant now = Instant.now();
		Instant min = Instant.MIN;
		Instant max = Instant.MAX;
		System.out.println("Now:" + now + ", Min:" + min + ", Max:" + max);
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			// ignore it.
		}
		Instant endTime = Instant.now();
		Duration diff = Duration.between(startTime, endTime);
		System.out.println(diff);
		System.out.println();
		
		LocalDate today = LocalDate.now();
		LocalDate endDayOfSchool = LocalDate.of(2018, Month.JUNE, 19);
		Period period = today.until(endDayOfSchool);
		System.out.println("period:" + period + ", Years:" + period.getYears() + ", Months:" + period.getMonths() + ", Days:" + period.getDays());
		
		long days = today.until(endDayOfSchool, ChronoUnit.DAYS);
		System.out.println("Days:" + days);
		
		List<Person> persons = PersonFactory.readPersons("Person.txt");
		if(persons != null) {
			persons.forEach(System.out::println);
		}
		
		System.out.println();
		LocalDate nextSunday = today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println("Next Sunday is on:" + nextSunday);
		
		// Time Handling
		System.out.println();
		LocalTime bedTime = LocalTime.of(22, 0);
		LocalTime wakeUpTime = bedTime.plusHours(8);
		System.out.println("Bed Time:" + bedTime + ", Wakeup Time:" + wakeUpTime);
		
		// Zone Handling
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println(allZoneIds);
		String indiaTimeZone = ZoneId.of("US/Pacific").toString();
		System.out.println(indiaTimeZone);
		
	}

}
