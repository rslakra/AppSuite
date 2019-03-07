/**
 * 
 */
package com.rslakra.jdk8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @Created Mar 6, 2019 10:01:39 AM
 * @version 1.0.0
 *
 */
public class IntStreamDemo {

	public static int reverse(int num) {
		return ((num >>> 28) | ((num >> 8) & 0xFF00) | ((num << 8) & 0xFF0000) | (num << 24));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> ageList = Arrays.asList(10, 23, 34, 15, 83, 39);
		System.out.println(ageList);
		OptionalInt minAge = ageList.stream().mapToInt(i -> i).min();
		System.out.println(minAge.getAsInt());
		OptionalInt maxAge = ageList.stream().mapToInt(i -> i).max();
		System.out.println(maxAge.getAsInt());

		int num = 23;
		System.out.println(reverse(num));

	}

}
