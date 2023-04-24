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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
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
