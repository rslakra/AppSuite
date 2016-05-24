package com.apparatus.testcases.utils;

import java.util.Arrays;

import com.apparatus.utils.ObjectHelper;

/**
 * 
 * @author Rohtash Singh Lakra
 * @created Apr 29, 2016 3:54:35 PM
 * 
 */
public class TestObjectHelper {
	
	public static void main(String[] args) {
		int[] values = { 6, 3, 0, 7, 4, 1, 9, 2, 8, 5};
		// bubble sort
		System.out.println("\nUnsorted:" + Arrays.toString(values));
		ObjectHelper.bubbleSort(values);
		System.out.println("Bubble sorted:" + Arrays.toString(values));
		
		// insertion sort
		values = new int[] { 6, 3, 0, 7, 4, 1, 9, 2, 8, 5};
		System.out.println("\nUnsorted:" + Arrays.toString(values));
		ObjectHelper.insertionSort(values);
		System.out.println("Insertion sorted:" + Arrays.toString(values));
		
		// merge sort
		values = new int[] { 6, 3, 0, 7, 4, 1, 9, 2, 8, 5};
		System.out.println("\nUnsorted:" + Arrays.toString(values));
		ObjectHelper.mergeSort(values);
		System.out.println("Merge sorted:" + Arrays.toString(values));
	}
	
}
