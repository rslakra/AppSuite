/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk8;

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
 * @author Rohtash Lakra
 * @date 01/30/2018 02:36:22 PM
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
     * @param args
     */
    public static void main(String[] args) {
        List<Person> listPersons = readPersonsNameAndAge("Person1.txt");
        if (listPersons != null) {
            listPersons.forEach(System.out::println);
        }
    }
}
