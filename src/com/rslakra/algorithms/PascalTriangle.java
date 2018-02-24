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
 * In mathematics, Pascal's triangle is a triangular array of the binomial
 * coefficients. In much of the Western world, it is named after French
 * mathematician Blaise Pascal, although other mathematicians studied it
 * centuries before him in India,[1] Persia (Iran), China, Germany, and
 * Italy.[2]
 * 
 * The rows of Pascal's triangle are conventionally enumerated starting with row
 * n = 0 at the top (the 0th row). The entries in each row are numbered from the
 * left beginning with k = 0 and are usually staggered relative to the numbers
 * in the adjacent rows. The triangle may be constructed in the following
 * manner: In row 0 (the topmost row), there is a unique nonzero entry 1. Each
 * entry of each subsequent row is constructed by adding the number above and to
 * the left with the number above and to the right, treating blank entries as 0.
 * For example, the initial number in the first (or any other) row is 1 (the sum
 * of 0 and 1), whereas the numbers 1 and 3 in the third row are added to
 * produce the number 4 in the fourth row.
 * 
 * @see https://en.wikipedia.org/wiki/Pascal%27s_triangle
 * 
 * @author Rohtash Singh Lakra
 * @date 10/19/2017 01:45:08 PM
 *
 */
public class PascalTriangle {

	public static void fillTriangle(int n, int[][] data) {
		for (int row = 0; row < data.length; row++) {
			for (int column = 0; column < data.length; column++) {
				if (row == 0 || column == 0) {
					data[row][column] = 1;
				} else {
					data[row][column] = data[row][column - 1] + data[row - 1][column];
				}
			}
		}
	}

	public static void printMatrix(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(java.util.Arrays.toString(data[i]));
		}
	}

	/**
	 * 
	 * @param data
	 */
	public static void printDiagonally(int[][] data) {
		// print left to bottom.
		for (int row = 0; row < data.length; row++) {
			for (int i = 0; i < ((data.length * 2) - row); i++) {
				System.out.print(" ");
			}
			for (int column = 0; column <= row; column++) {
				if (row == 0 || column == 0) {
					System.out.print(data[row][column] + " ");
				} else {
					System.out.print(data[row - column][column] + " ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param n
	 */
	public static void printDiagonallyByFormula(int n) {
		// print left to bottom.
		for (int row = 0; row < n; row++) {
			for (int i = 0; i < ((n * 2) - row); i++) {
				System.out.print(" ");
			}
			int lastValue = 0;
			for (int column = 0; column <= row; column++) {
				if (row == 0 || column == 0) {
					lastValue = 1;
				} else {
					lastValue = (lastValue * (row + 1 - column) / column);
				}
				System.out.print(lastValue + " ");
			}
			System.out.println();
		}
	}

	public static void printDiagonallyByFormulaLeftJustified(int n) {
		// print left to bottom.
		for (int row = 0; row < n; row++) {
			int lastValue = 0;
			for (int column = 0; column <= row; column++) {
				if (row == 0 || column == 0) {
					lastValue = 1;
				} else {
					lastValue = (lastValue * (row + 1 - column) / column);
				}
				System.out.print(lastValue + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Generate the Pascal triangle left aligned and print the sum of diagonal
	 * numbers.
	 * 
	 * @param n
	 */
	public static void fibonacciSeriesWithPasalTriangle(int n) {
		int[][] data = new int[n][n];
		for (int row = 0; row < n; row++) {
			int lastValue = 0;
			for (int column = 0; column <= row; column++) {
				if (row == 0 || column == 0) {
					lastValue = 1;
				} else {
					lastValue = (lastValue * (row + 1 - column) / column);
				}
				data[row][column] = lastValue;
			}
		}

		// Matrix.printMatrix(data);
		int[] arr = new int[n];
		int ctr = 0;
		for (int row = 0; row < n; row++) {
			int sum = 0;
			for (int column = 0; column <= row; column++) {
				if (row == 0 || column == 0) {
					sum += data[row][column];
				} else {
					sum += data[row - column][column];
				}
			}
			arr[ctr++] = sum;
		}

		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.close();
		int[][] data = new int[n][n];
		fillTriangle(n, data);
		printMatrix(data);
		System.out.println();
		printDiagonally(data);
		System.out.println();
		printDiagonallyByFormula(n);
		System.out.println();
		printDiagonallyByFormulaLeftJustified(n);
		System.out.println();
		fibonacciSeriesWithPasalTriangle(n);
	}

}
