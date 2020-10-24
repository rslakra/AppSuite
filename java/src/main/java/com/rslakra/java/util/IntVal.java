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
package com.rslakra.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-10 01:22:51 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class IntVal implements Comparable {

	private int value;

	/**
	 * 
	 * @param value
	 */
	public IntVal(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getValue() ^ 31;
	}

	/**
	 * 
	 * @param o
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return ((IntVal) o).getValue() == this.getValue();
	}

	public String toString() {
		return String.valueOf(value);
		// return "IntVal, value : " + getValue();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntVal intVal = new IntVal(10);
		System.out.println(intVal);

		List<IntVal> list = new ArrayList<IntVal>();
		list.add(new IntVal(10));
		list.add(new IntVal(4));
		list.add(new IntVal(23));
		list.add(new IntVal(2));
		list.add(new IntVal(11));
		System.out.println("unsorted\n" + list);

		Collections.sort(list);
		System.out.println("sorted\n" + list);
	}

	/**
	 * 
	 * @param o
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		IntVal intVal = (IntVal) o;
		// System.out.println(value + " == " + intVal.getValue());
		if (value == intVal.getValue()) {
			return 0;
		} else {
			return (value > intVal.getValue() ? 1 : -1);
		}
	}
}