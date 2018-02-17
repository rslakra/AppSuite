package com.rslakra.algorithms.arrays;

/**
 * Provides an algorithms of an array.
 *
 * @author Rohtash Singh Lakra
 * @date 06/20/2017 08:41:33 PM
 */
public final class Arrays {
	
	/**
	 * Peak b is only the peak if b >= a and b >= c.
	 * 
	 * Divide & Concur.
	 * 
	 * @param values
	 * @return
	 */
	public static int peakFinder(int[] values) {
		int peakValue = -1;
		for(int i = 0; i < values.length; i++) {
			if(i == 0) {
				if(values[i + 1] >= values[i]) {
					peakValue = values[i];
				}
			} else if(i == values.length - 1) {
				if(values[i] >= values[i - 1]) {
					peakValue = values[i];
				}
			} else if(values[i] >= values[i - 1] && values[i] >= values[i + 1]) {
				peakValue = values[i];
			}
		}
		
		return peakValue;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] values = { 2, 3, 5, 8, 9, 4, 3 };
		int peak = Arrays.peakFinder(values);
		System.out.println("Peak:" + peak);
	}
	
}
