/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * TODO Auto-generated comments.
 *
 * @author rohtash.singh
 * @version May 9, 2006
 *
 */
public class TestRuntimeAndFloat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int j = 0;
		for (int i = 0; i < 20; i++) {
			j += 1;
		}
		System.out.println(j);

		try {
			// Runtime.getRuntime().exec("command.com /c dir");
			Process p = Runtime.getRuntime().exec("cmd /c dir");
			DataInputStream procIn = new DataInputStream(p.getInputStream());
			while (true) {
				String line = procIn.readLine();
				if (line == null)
					break;
				System.out.println(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Float.NaN == Float.NaN :" + (Float.NaN == Float.NaN));
		System.out.println("Float.POSITIVE_INFINITY == Float.POSITIVE_INFINITY :"
				+ (Float.POSITIVE_INFINITY == Float.POSITIVE_INFINITY));
		System.out.println("Float.NEGATIVE_INFINITY == Float.NEGATIVE_INFINITY :"
				+ (Float.NEGATIVE_INFINITY == Float.NEGATIVE_INFINITY));
		System.out.println("Float.MIN_VALUE == Float.MIN_VALUE :" + (Float.MIN_VALUE == Float.MIN_VALUE));

	}

}
