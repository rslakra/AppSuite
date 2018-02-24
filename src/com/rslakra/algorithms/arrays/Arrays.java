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
		for (int i = 0; i < values.length; i++) {
			if (i == 0) {
				if (values[i + 1] >= values[i]) {
					peakValue = values[i];
				}
			} else if (i == values.length - 1) {
				if (values[i] >= values[i - 1]) {
					peakValue = values[i];
				}
			} else if (values[i] >= values[i - 1] && values[i] >= values[i + 1]) {
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
