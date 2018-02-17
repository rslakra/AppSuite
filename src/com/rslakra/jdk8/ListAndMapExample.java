/**
 * 
 */
package com.rslakra.jdk8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Singh Lakra
 */
public class ListAndMapExample {
	
	/**
	 * Returns the map of the persons grouped by age.
	 * 
	 * @param persons
	 * @return
	 */
	private static Map<Integer, List<Person>> mapByAge(List<Person> persons) {
		return persons.stream().collect(Collectors.groupingBy(Person::getAge));
	}
	
	/**
	 * Merges the source map into the target map.
	 * 
	 * @param mapSource
	 * @param mapTarget
	 */
	private static void mergeMapsByAge(Map<Integer, List<Person>> mapSource, Map<Integer, List<Person>> mapTarget) {
		mapSource.entrySet().stream().forEach(entry -> mapTarget.merge(entry.getKey(), entry.getValue(), (oldEntry, newEntry) -> {
			oldEntry.addAll(newEntry);
			return oldEntry;
		}));
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
		
		System.out.println("List First");
		List<Person> listFirst = persons.subList(0, 10);
		listFirst.forEach(System.out::println);
		System.out.println("Records:" + listFirst.size());
		System.out.println("Group Persons of First List By Age");
		Map<Integer, List<Person>> mapFirstList = mapByAge(listFirst);
		mapFirstList.forEach((age, list) -> System.out.println(age + "=>" + list));
		System.out.println();
		
		System.out.println("List Second");
		List<Person> listSecond = persons.subList(10, persons.size());
		listSecond.forEach(System.out::println);
		System.out.println("Records:" + listSecond.size());
		System.out.println("Group Persons of Second List By Age");
		Map<Integer, List<Person>> mapSecondList = mapByAge(listSecond);
		mapSecondList.forEach((age, list) -> System.out.println(age + "=>" + list));
		System.out.println();
		System.out.println();
		
		System.out.println("Merged Groups of Persons of 2 maps into one map By Age");
		mergeMapsByAge(mapFirstList, mapSecondList);
		mapSecondList.forEach((age, list) -> System.out.println(age + "=>" + list));
	}
	
}
