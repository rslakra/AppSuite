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
package com.rslakra.algorithms.sort;

import java.util.Arrays;

/**
 * This class handles the sorting.
 *
 * @author Rohtash Singh Lakra
 * @date 05/13/2017 03:51:19 PM
 *
 */
public final class Sorts {

	/**
	 * 
	 * @param values
	 */
	public static void mergeSort(int[] values) {
		int[] temp = new int[values.length];
		mergeSort(values, 0, values.length - 1, temp);
	}

	/**
	 * 
	 * @param values
	 * @param low
	 * @param high
	 */
	private static void mergeSort(int[] values, int low, int high, int[] temp) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(values, low, mid, temp);
			mergeSort(values, mid + 1, high, temp);
			merge(values, low, mid, high, temp);
		}
	}

	/**
	 * 
	 * @param values
	 * @param lArray
	 * @param rArray
	 */
	private static void merge(int[] values, int low, int middle, int high, int[] temp) {
		int index = low;
		int lIndex = low;
		int rIndex = middle + 1;

		// copy the existing sorted values to temp
		System.arraycopy(values, 0, temp, 0, values.length);

		// merge both (left and right array values
		while (lIndex <= middle && rIndex <= high) {
			if (temp[lIndex] < temp[rIndex]) {
				values[index] = temp[lIndex];
				lIndex++;
			} else {
				values[index] = temp[rIndex];
				rIndex++;
			}
			index++;
		}

		// merge left array remaining items, if any
		while (lIndex <= middle) {
			values[index] = temp[lIndex];
			lIndex++;
			index++;
		}

		// merge right array remaining items, if any.
		while (rIndex <= high) {
			values[index] = temp[rIndex];
			rIndex++;
			index++;
		}
	}

	/**
	 * 
	 * @param values
	 */
	public static void mergeSortWithRecursion(int[] values) {
		int[] result = mergeSort(values, values.length);
		System.arraycopy(result, 0, values, 0, result.length);
	}

	/**
	 * 
	 * @param values
	 * @param length
	 * @return
	 */
	private static int[] mergeSort(int[] values, int length) {
		if (values.length == 1) {
			return values;
		} else {
			int mid = length / 2;
			int[] lArray = new int[mid];
			System.arraycopy(values, 0, lArray, 0, mid);
			int[] rArray = new int[length - mid];
			System.arraycopy(values, mid, rArray, 0, (length - mid));
			return merge(mergeSort(lArray, mid), mergeSort(rArray, (length - mid)));
		}
	}

	/**
	 * 
	 * @param values
	 * @param lArray
	 * @param rArray
	 */
	private static int[] merge(int[] lArray, int[] rArray) {
		int lIndex = 0;
		int rIndex = 0;
		int index = 0;
		int[] values = new int[lArray.length + rArray.length];

		// merge both (left and right array values
		while (lIndex < lArray.length && rIndex < rArray.length) {
			if (lArray[lIndex] < rArray[rIndex]) {
				values[index] = lArray[lIndex];
				lIndex++;
			} else {
				values[index] = rArray[rIndex];
				rIndex++;
			}
			index++;
		}

		// merge left array remaining items, if any
		while (lIndex < lArray.length) {
			values[index] = lArray[lIndex];
			lIndex++;
			index++;
		}

		// merge right array remaining items, if any.
		while (rIndex < rArray.length) {
			values[index] = rArray[rIndex];
			rIndex++;
			index++;
		}

		return values;
	}

	/**
	 * Sorts using bubble sort.
	 * 
	 * @param values
	 */
	public static void bubbleSort(int[] values) {
		for (int i = 0; i < values.length - 1; i++) {
			boolean swapped = false;
			for (int j = 0; j < values.length - 1 - i; j++) {
				if (values[j] > values[j + 1]) {
					int temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
					swapped = true;
				}
			}

			if (!swapped) {
				// array is already sorted, nothing to do.
				break;
			}
		}
	}

	/**
	 * The main function for testing.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] values = { 3, 1, 6, 4, 9, 5, 0, 8 };
		System.out.println("Values:" + Arrays.toString(values));
		// Sorts.mergeSort(values);
		// Sorts.mergeSortWithRecursion(values);
		// System.out.println("Sorted:" + Arrays.toString(values));
		Sorts.bubbleSort(values);
		System.out.println("Sorted:" + Arrays.toString(values));

	}

}
