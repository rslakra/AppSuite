/******************************************************************************
 * Copyright (C) Devamatre Technologies 2018
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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-24 06:00:32 PM
 * @version 1.0.0
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
