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
package com.rslakra.jdk8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-26 07:42:39 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class CollectorsExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// read persons
		List<Person> persons = new ArrayList<>();
		try (BufferedReader bReader = new BufferedReader(
				new InputStreamReader(CollectorsExample.class.getResourceAsStream("Person.txt")));
				Stream<String> sPersons = bReader.lines();) {

			// fill persons list and print
			sPersons.map(line -> {
				String[] tokens = line.split(" ");
				Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
				persons.add(person);
				return person;
			}).forEach(System.out::println);

			// comparator
			Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
			System.out.println();
			// find yougest person in the list
			Optional<Person> youngPerson = persons.stream().filter(person -> person.getAge() > 25).min(ageComparator);
			System.out.println(youngPerson);

			System.out.println();
			// find oldest person in the list
			Optional<Person> olderPerson = persons.stream().max(ageComparator);
			System.out.println(olderPerson);

			System.out.println();
			// Find person of the same age.
			Map<Integer, List<Person>> personGroupingByAge = persons.stream()
					.collect(Collectors.groupingBy(Person::getAge));
			System.out.println(personGroupingByAge);

			// Find how many persons of the same age.
			Map<Integer, Long> personCountByAge = persons.stream()
					.collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
			System.out.println(personCountByAge);

			// Find person names based on the same age.
			Map<Integer, List<String>> personNamesByAge = persons.stream().collect(
					Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));
			System.out.println(personNamesByAge);

			// Find person names based on the same age and sort values by name.
			Map<Integer, Set<String>> sortedPersonNamesByAge = persons.stream().collect(Collectors.groupingBy(
					Person::getAge, Collectors.mapping(Person::getName, Collectors.toCollection(TreeSet::new))));
			System.out.println(sortedPersonNamesByAge);

			// Find person names based on the same age and keep as string
			// separated by ",".
			Map<Integer, String> namesByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge,
					Collectors.mapping(Person::getName, Collectors.joining(","))));
			System.out.println(namesByAge);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
