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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2018-01-24 06:00:32 PM
 * @since 1.0.0
 */
public class FlatMapExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> primes = Arrays.asList(1, 2, 3, 5, 7, 11);
        List<Integer> evens = Arrays.asList(2, 4, 6);
        List<Integer> odds = Arrays.asList(1, 3, 5);
        List<List<Integer>> numbers = Arrays.asList(primes, evens, odds);

        // Consumer<Integer> output = System.out::println;
        Function<List<Integer>, Integer> sizeFunction = List::size;
        numbers.stream().map(sizeFunction).forEach(System.out::println);

        System.out.println("\nPrint each streams");
        Function<List<Integer>, Stream<Integer>> flatMapper = list -> list.stream();
        numbers.stream().map(flatMapper).forEach(System.out::println);

        System.out.println("\nPrint flattened lists");
        numbers.stream().flatMap(flatMapper).forEach(System.out::println);

        // reduction/aggregation
        System.out.println("\nPrime Numbers and there sum");
        primes.forEach(System.out::println);
        BinaryOperator<Integer> sumOperator = (age1, age2) -> age1 + age2;
        Integer identity = 0;
        // primes = Collections.EMPTY_LIST;
        // primes = Arrays.asList(1);
        Integer sum = primes.stream().reduce(identity, sumOperator);
        System.out.println("======\n" + sum);

        BinaryOperator<Integer> maxOperator = (number, another) -> number > another ? number : another;
        Integer max = primes.stream().reduce(identity, maxOperator);
        System.out.println("======\n" + max);

        BinaryOperator<Integer> minOperator = (number, another) -> number < another ? number : another;
        Integer min = primes.stream().reduce(identity, minOperator);
        System.out.println("======\n" + min);

        System.out.println("\nPrime Numbers and there max");
        Optional<Integer> optMax = primes.stream().max(Comparator.naturalOrder());
        System.out.println(optMax);

        System.out.println("\nEven Numbers sum");
        Integer sumEvens = evens.stream().reduce(0, Integer::sum);
        System.out.println(sumEvens);

        System.out.println("\nEven Numbers Max");
        Optional<Integer> maxEvens = evens.stream().reduce(Integer::max);
        System.out.println(maxEvens);

    }

}
