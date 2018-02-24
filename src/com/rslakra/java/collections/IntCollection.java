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
package com.rslakra.java.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-10 01:24:31 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class IntCollection {

	Collection col1, col2;

	public IntCollection() {
		col1 = new ArrayList();
		for (int i = 1; i < 6; i++) {
			col1.add(new IntVal(i));
		}

		col2 = new ArrayList();
		for (int i = 4; i < 10; i++) {
			col2.add(new IntVal(i));
		}
	}

	public Collection getCol1() {
		return col1;
	}

	public Collection getCol2() {
		return col2;
	}

	public void print(Collection col) {
		for (Iterator iter = col.iterator(); iter.hasNext();) {
			IntVal element = (IntVal) iter.next();
			System.out.println(element);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntCollection intCol = new IntCollection();
		System.out.println("============ Initial Col 1 ===================");
		intCol.print(intCol.getCol1());
		System.out.println("============ Initial Col 2 ===================");
		intCol.print(intCol.getCol2());
		// System.out.println("============ After Addding 2 into 1
		// ===================");
		// intCol.getCol1().addAll(intCol.getCol2());
		// intCol.print(intCol.getCol1());
		System.out.println("============ After Removing 2 from 1 ===================");
		intCol.getCol1().removeAll(intCol.getCol2());
		intCol.print(intCol.getCol1());
		// System.out.println("============ Retain All in 1
		// ===================");
		// intCol.getCol1().retainAll(intCol.getCol2());
		// intCol.print(intCol.getCol1());
	}

}
