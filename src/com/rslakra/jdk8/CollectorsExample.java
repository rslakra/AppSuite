/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
				Person person = new Person(tokens[0]);
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
