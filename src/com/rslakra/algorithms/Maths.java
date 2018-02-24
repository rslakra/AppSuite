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
		if (disk == 1) {

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
