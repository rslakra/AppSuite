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
package com.rslakra.java;

import java.text.DecimalFormat;

/**
 * TODO Auto-generated comments.
 * 
 * @author rohtash.singh
 * @version May 25, 2006
 * 
 */
public class Number {

	public static void main(String args[]) {
		double d = 999999999.9999999999999999999;
		String mask = "000000000.0000000000000000000";
		// jdk1.1
		DecimalFormat df = new DecimalFormat(mask);

		// double
		System.out.println("d : " + d);
		Double dub = new Double(d);
		long ld = dub.doubleToLongBits(d);
		System.out.println(":" + new DecimalFormat(mask).format((dub.longBitsToDouble(ld) - 1)));

		String result = dub.toString();
		System.out.println(result);
		int index = result.indexOf("E");
		// int maxValue = getInt(result.substring(index + 1));
		// System.out.println("maxValue : " + maxValue);
		// int mantisa = getInt(result.substring(0, index));
		// int value = 1;
		// System.out.println("value : " + value);
		// for (int i = 0; i < maxValue; i++) {
		// value *= 10;
		// System.out.println("new Value : " + value);
		// }
		// System.out.println("final Value : " + (value - mantisa));

		System.out.println(getByte());
	}

	// private static int getInt(String str) {
	// try {
	// return Integer.parseInt(str);
	// }catch (NumberFormatException e) {
	// e.printStackTrace();
	// return -1;
	// }
	// }

	static byte getByte() {
		final char c = 3;
		byte b = c;
		return 1;
	}
}
