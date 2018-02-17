package com.rslakra.algorithms;

import java.math.BigInteger;

/**
 * 
 *
 * @author Rohtash Singh Lakra
 * @date 05/15/2017 01:24:49 PM
 */
public final class Maths {
	
	/**
	 * 
	 * @param disk
	 * @param source
	 * @param target
	 * @param auxilary
	 */
	public static void powerOfHanoi(int disk, int[] source, int[] target, int[] auxilary) {
		if(disk == 1) {
			
		}
	}
	
	/**
	 * Returns the power of the number.
	 * 
	 * @param number
	 * @param exponent
	 * @return
	 */
	public static double power(int number, int exponent) {
		System.out.println("power(" + number + ", " + exponent + ")");
		return (exponent > 0 ? number * power(number, exponent - 1) : 1);
	}
	
	/**
	 * 
	 * @param number
	 * @param exponent
	 * @return
	 */
	public static String pow(int number, int exponent) {
		return BigInteger.valueOf(number).pow(exponent).toString();
		// return (BigInteger.valueOf(number).shiftLeft(exponent).toString());
	}
	
	public static int[] gcm(int value) {
		return null;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int number = 2;
		int pow = 128;
		System.err.println("Power " + pow + " of " + number + "=" + Maths.power(number, pow));
		System.err.println("Power " + pow + " of " + number + "=" + Maths.pow(number, pow));
	}
	
}
