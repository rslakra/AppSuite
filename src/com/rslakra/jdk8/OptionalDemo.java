/**
 * 
 */
package com.rslakra.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @Created Mar 8, 2019 9:49:32 PM
 * @version 1.0.0
 *
 */
public class OptionalDemo<T> {

	private List<T> listOfOptional;

	public void initOptional(int limit, boolean value) {
		System.out.println("IntStream.range:");
		IntStream.range(1, limit).forEach(System.out::println);
		System.out.println("IntStream.rangeClosed:");
		IntStream.rangeClosed(1, limit).forEach(System.out::println);
		System.out.println("IntStream.iterate-Even:");
		IntStream.iterate(2, i -> i + 2).limit(limit).forEach(System.out::println);
		System.out.println("IntStream.iterate-Odd:");
		IntStream.iterate(1, i -> i + 2).limit(limit).forEach(System.out::println);
		System.out.println("IntStream.iterate-Counting:");
		IntStream.iterate(1, i -> i + 1).limit(limit).forEach(System.out::println);
	}

	/**
	 * 
	 * @param limit
	 */
	public void counting(int limit) {
		System.out.println("Counting [1-" + limit + "]");
		IntStream.rangeClosed(1, limit).forEach(System.out::println);
		System.out.println();

	}

	/**
	 * 
	 * @param limit
	 */
	public void even(int limit) {
		System.out.println(limit + " even numbers:");
		IntStream.iterate(2, i -> i + 2).limit(limit).forEach(System.out::println);
		System.out.println();
	}

	/**
	 * 
	 * @param limit
	 */
	public void squared(int limit) {
		System.out.println(limit + " squared numbers:");
		IntStream.rangeClosed(1, limit).map(i -> i * i).forEach(System.out::println);
		System.out.println();
	}

	/**
	 * 
	 * @param limit
	 */
	public void fibonacci(int limit) {
		System.out.println(limit + " even numbers:");
		IntStream.iterate(0, i -> i + i).limit(limit).forEach(System.out::println);
		System.out.println();
	}

	/**
	 * 
	 * @param limit
	 */
	public void odd(int limit) {
		System.out.println(limit + " odd numbers:");
		IntStream.iterate(1, i -> i + 2).limit(limit).forEach(System.out::println);
		System.out.println();
	}

	/**
	 * 
	 * @param limit
	 */
	public void initOptional(int limit) {
		listOfOptional = (List<T>) IntStream.rangeClosed(1, limit).boxed().collect(Collectors.toList());
	}

	/**
	 * 
	 * @param list
	 */
	public void initOptional(List<T> list) {
		listOfOptional = list.stream().collect(Collectors.toList());
	}

	/**
	 * 
	 */
	public void print() {
		listOfOptional.forEach(System.out::println);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OptionalDemo<Integer> demoOptional = new OptionalDemo<>();
		demoOptional.initOptional(Arrays.asList());
		demoOptional.print();

		demoOptional.initOptional(10);
		demoOptional.print();
		demoOptional.counting(10);
		demoOptional.even(10);
		demoOptional.odd(10);
		demoOptional.squared(5);

	}

}
