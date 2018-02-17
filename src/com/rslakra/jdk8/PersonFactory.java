package com.rslakra.jdk8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 01/30/2018 02:36:22 PM
 * 
 */
public final class PersonFactory {

	/**
	 * Reads the persons file and returns the list of persons. If the file does
	 * not available, the null is returned.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Person> readPersons(String fileName) {
		List<Person> listPersons = null;
		if (fileName != null) {
			InputStream streamPersons = PersonFactory.class.getResourceAsStream(fileName);
			if (streamPersons != null) {
				try (BufferedReader bReader = new BufferedReader(new InputStreamReader(streamPersons));
						Stream<String> dataStream = bReader.lines();) {
					listPersons = dataStream.map(line -> {
						Person newPerson = null;
						String[] personTokens = line.split(" ");
						if (personTokens != null && personTokens.length > 4) {
							newPerson = new Person(personTokens[0]);
							int year = Integer.parseInt(personTokens[1]);
							int month = Integer.parseInt(personTokens[2]);
							int dayOfMonth = Integer.parseInt(personTokens[3]);
							newPerson.setBirthDate(LocalDate.of(year, month, dayOfMonth));
							newPerson.setSex(personTokens[4].charAt(0));
						}

						return newPerson;
					}).collect(Collectors.<Person>toList());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return listPersons;
	}

	/**
	 * Returns the list of persons after reading from the person.txt file.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Person> readPersonsNameAndAge(String fileName) {
		List<Person> persons = null;
		if (fileName != null) {
			URL url = PersonFactory.class.getResource(fileName);
			if (url != null) {
				Path path = Paths.get(url.getPath());
				try (Stream<String> listPersons = Files.readAllLines(path).stream()) {
					persons = listPersons.map(line -> {
						Person newPerson = null;
						String[] tokens = line.split(" ");
						if (tokens != null && tokens.length > 0) {
							newPerson = new Person(tokens[0]);
						}

						return newPerson;
					}).collect(Collectors.<Person>toList());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return persons;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> listPersons = readPersonsNameAndAge("Person1.txt");
		if (listPersons != null) {
			listPersons.forEach(System.out::println);
		}
	}
}
