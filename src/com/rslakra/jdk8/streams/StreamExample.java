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
package com.rslakra.jdk8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rslakra.jdk8.Person;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-24 09:07:55 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class StreamExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		Person lakra = new Person("Lakra");
		Person java = new Person("Java");
		persons.add(new Person("Rohtash"));
		persons.add(new Person("Singh"));
		persons.add(lakra);
		persons.add(java);

		Predicate<Person> personFilter = (person) -> person.getAge() > 40;
		persons.stream().filter(personFilter).forEach(System.out::println);

		System.out.println();
		persons.add(lakra);
		Predicate<Person> isLakra = Predicate.isEqual(lakra);
		Predicate<Person> isJava = Predicate.isEqual(java);

		Stream<Person> prdicates = persons.stream().filter(isLakra.or(isJava));
		prdicates.forEach((person) -> System.out.println(person));

		// weekdays
		System.out.println();
		Stream<String> weekdays = Stream.of("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
				"Saturday");
		Predicate<String> strMonday = Predicate.isEqual("Monday");
		Predicate<String> strFriday = Predicate.isEqual("Friday");
		List<String> days = new ArrayList<>();
		weekdays.filter(strMonday.or(strFriday)).peek(days::add).collect(Collectors.toList());
		// forEach(System.out::println);
		System.out.println();
		days.forEach(System.out::println);

		// Stream.of("one", "two", "three", "four")
		// .filter(e -> e.length() > 3)
		// .peek(e -> System.out.println("Filtered value: " + e))
		// .map(String::toUpperCase)
		// .peek(e -> System.out.println("Mapped value: " + e))
		// .collect(Collectors.toList());
	}

}
