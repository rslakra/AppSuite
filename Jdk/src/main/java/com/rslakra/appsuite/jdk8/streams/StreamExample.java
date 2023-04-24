/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
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
package com.rslakra.appsuite.jdk8.streams;

import com.rslakra.appsuite.jdk8.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2018-01-24 09:07:55 AM
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
