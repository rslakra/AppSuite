/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
package com.rslakra.algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * In mathematics, the Fibonacci numbers are the numbers in the following
 * integer sequence, called the Fibonacci sequence, and characterized by the
 * fact that every number after the first two is the sum of the two preceding
 * ones:[1][2]
 * 
 * {1, 1, 2, 3, 5, 8, 13, 21, 34, 89, 144, ...}, or
 * 
 * Often, especially in modern usage, the sequence is extended by one more
 * initial term:
 * 
 * {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 89, 144, ...}
 * 
 * The Fibonacci spiral: an approximation of the golden spiral created by
 * drawing circular arcs connecting the opposite corners of squares in the
 * Fibonacci tiling;[4] this one uses squares of sizes 1, 1, 2, 3, 5, 8, 13 and
 * 21. By definition, the first two numbers in the Fibonacci sequence are either
 * 1 and 1, or 0 and 1, depending on the chosen starting point of the sequence,
 * and each subsequent number is the sum of the previous two.
 * 
 * The sequence Fn of Fibonacci numbers is defined by the recurrence relation: {
 * fib(n) = fib(n-1) + fib(n-2) with seed values (1) (2)} fib(1) = 1, fib(2) = 1
 * or fib(0) = 0, fib(1) = 1
 * 
 * @see https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * @author Rohtash Singh Lakra
 * @date 10/19/2017 06:53:01 AM
 *
 */
public class FibonacciNumbers {

	public static void printFibonacci(int n) {
		if (n == 0) {
			System.out.print(0);
		} else if (n == 1) {
			System.out.print(0 + " " + 1);
		} else {
			int left = 0;
			int right = 1;
			System.out.print(left + " " + right + " ");
			for (int i = 2; i <= n; i++) {
				int sum = left + right;
				System.out.print(sum + " ");
				left = right;
				right = sum;
			}
		}
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int fibSeries(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fibSeries(n - 1) + fibSeries(n - 2);
		}
	}

	/**
	 * 
	 * @param n
	 */
	public static void printFibonacciRecursion(int n) {
		for (int i = 0; i <= n; i++) {
			System.out.print(fibSeries(i) + " ");
		}
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int fibSeries(int n, int[] arr) {
		if (n <= 1) {
			if (n == 1 && arr[n] == 0) {
				arr[n] = n;
			}
		} else if (n > 1 && arr[n] == 0) {
			arr[n] = fibSeries(n - 1) + fibSeries(n - 2);
		}

		return arr[n];
	}

	/**
	 * 
	 * @param n
	 */
	public static void printFibonacciDynamically(int n) {
		int[] arr = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			System.out.print(fibSeries(i, arr) + " ");
		}
		System.out.println();
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.close();
		printFibonacci(n);
		System.out.println();
		printFibonacciRecursion(n);
		System.out.println();
		printFibonacciDynamically(n);
	}

}
