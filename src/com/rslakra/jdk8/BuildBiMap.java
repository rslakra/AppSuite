/**
 * 
 */
package com.rslakra.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Singh Lakra
 */
public class BuildBiMap {
	
	/**
	 * Returns the Map of the persons grouped by age from the list.
	 * 
	 * @param persons
	 * @return
	 */
	private static Map<Integer, List<Person>> mapByAge(List<Person> persons) {
		return persons.stream().collect(Collectors.groupingBy(Person::getAge));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> persons = PersonFactory.readPersons("Person.txt");
		persons.forEach(System.out::println);
		System.out.println("Records:" + persons.size());
		System.out.println();
		System.out.println();
		
		System.out.println("Persons Grouped by Age:");
		Map<Integer, List<Person>> mapByAge = mapByAge(persons);
		mapByAge.forEach((age, list) -> System.out.println(age + " => " + list));
		
		// Now create BI map.
		Map<Integer, Map<Character, List<Person>>> biMap = new HashMap<>();
		persons.forEach(person -> biMap.computeIfAbsent(person.getAge(), HashMap::new).merge(person.getSex(), new ArrayList<>(Arrays.asList(person)), (oldEntry, newEntry) -> {
			oldEntry.addAll(newEntry);
			return oldEntry;
		}));
		
		System.out.println("Bi Map:");
		biMap.forEach((age, map) -> System.out.println(age + " => " + map));
	}
	
}
