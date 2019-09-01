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

/**
 * <p>Title: @see classname </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class TestStaticExample {
	public TestStaticExample() {
	}

	public static void main(String[] args) {
		TestStaticExample test1 = new TestStaticExample();
		// StaticTest st1 = new StaticTest();
		// StaticTest st2 = new StaticTest();
		// System.out.println("st1_a : " + st1.a);
		// System.out.println("st1_Incr : " + st1.Incr());
		// /**
		// * Flushing
		// */
		// System.out.flush();
		//
		// System.out.println("st2_a : " + st2.a);
		// System.out.println("st2_Incr : " + st2.Incr());
		// System.out.println("StaticTest_Incr : " + StaticTest.Incr());
		// System.out.println("System.out.getClass()" + System.out.getClass());

		TestStatic ts1 = new TestStatic();
		TestStatic ts2 = new TestStatic();
		ts1.k = 10;
		ts2.k = 20;
		System.out.println("ts1_k : " + ts1.k + " ts2_k : " + ts2.k);
		/**
		 * Assignment in Primitives is quite straightforward. Since the
		 * primitive holds the actual value and not a reference to an object,
		 * When you assign primitive, you copy the contents form one place to
		 * another as in following statement. here value of ts2.k is copied into
		 * another location of ts1.k variable.
		 */
		ts1.k = ts2.k;
		/**
		 * When you assign objects, however, things changes.
		 */
		ts1 = ts2;
		System.out.println("ts1_k : " + ts1.k + " ts2_k : " + ts2.k);
		ts1.k = 50;
		System.out.println("ts1_k : " + ts1.k + " ts2_k : " + ts2.k);

		/**
		 * When u want to stop the fulshing the screen
		 */
		// try {
		// System.in.read() ;
		// }catch(Exception e) {
		// }

		System.out.println("Float.NaN == Float.NaN :"
				+ (Float.NaN == Float.NaN));
		System.out
				.println("Float.POSITIVE_INFINITY == Float.POSITIVE_INFINITY :"
						+ (Float.POSITIVE_INFINITY == Float.POSITIVE_INFINITY));
		System.out
				.println("Float.NEGATIVE_INFINITY == Float.NEGATIVE_INFINITY :"
						+ (Float.NEGATIVE_INFINITY == Float.NEGATIVE_INFINITY));
		System.out.println("Float.MIN_VALUE == Float.MIN_VALUE :"
				+ (Float.MIN_VALUE == Float.MIN_VALUE));

	}
}

class TestStatic {
	int k = 0;
}

class StaticTest {
	static int a = 0;

	public StaticTest() {
	}

	static int Incr() {
		a++;
		return a;
	}
}
