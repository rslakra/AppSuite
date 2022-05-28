/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
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
package com.rslakra.jdk8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-24 07:57:31 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class LambdaExpression {

	/**
	 * Traditional Approach. Return true if the number is prime otherwise false.
	 * 
	 * @param number
	 * @return
	 */
	private static boolean isPrimeTraditional(int number) {
		if (number < 2) {
			return false;
		}

		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	private static boolean isPrimeDeclarative(int number) {
		return (number > 1 && IntStream.range(2, number).noneMatch(index -> number % index == 0));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("Rohtash");
		names.add("Singh");
		names.add("Lakra");
		names.add("Java");
		System.out.println(names);

		// after sorting
		Comparator<String> lengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
		System.out.println("After Sorting with lengthComparator.");
		names.sort(lengthComparator);
		System.out.println(names);

		// after sorting
		Comparator<String> stringComparator = (s1, s2) -> s1.compareTo(s2);
		System.out.println("After Sorting with stringComparator.");
		names.sort(stringComparator);
		System.out.println(names);

		int number = 1;
		System.out.println(number + " is isPrimeTraditional:" + LambdaExpression.isPrimeTraditional(number));
		System.out.println(number + " is isPrimeDeclarative:" + LambdaExpression.isPrimeDeclarative(number));
		number = 7;
		System.out.println(number + " is isPrimeTraditional:" + LambdaExpression.isPrimeTraditional(number));
		System.out.println(number + " is isPrimeDeclarative:" + LambdaExpression.isPrimeDeclarative(number));
		number = 9;
		System.out.println(number + " is isPrimeTraditional:" + LambdaExpression.isPrimeTraditional(number));
		System.out.println(number + " is isPrimeDeclarative:" + LambdaExpression.isPrimeDeclarative(number));

	}

}
