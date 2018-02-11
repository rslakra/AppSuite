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

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-10 10:10:29 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public final class PersonReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> listPersons = PersonReader.readPersonsNameAndAge("Person1.txt");
		if (listPersons != null) {
			listPersons.forEach(System.out::println);
		}

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
			URL url = PersonReader.class.getResource(fileName);
			if (url != null) {
				Path path = Paths.get(url.getPath());
				try (Stream<String> listPersons = Files.readAllLines(path).stream()) {
					persons = listPersons.map(line -> {
						Person newPerson = null;
						String[] tokens = line.split(" ");
						if (tokens != null && tokens.length > 0) {
							newPerson = new Person(tokens[0], Integer.parseInt(tokens[1]));
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

}
