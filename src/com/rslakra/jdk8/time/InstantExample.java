/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
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
