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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
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
