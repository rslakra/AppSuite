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
package com.rslakra.jdk.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-23 10:30:16 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ListExample {

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		List list = new ArrayList();
		list.add("Bernadine");
		list.add("Elizabeth");
		list.add("Gene");
		list.add("Elizabeth");
		list.add("Clara");
		System.out.println(list);
		System.out.println("2: " + list.get(2));
		System.out.println("0: " + list.get(0));
		LinkedList queue = new LinkedList();
		queue.addFirst("Bernadine");
		queue.addFirst("Elizabeth");
		queue.addFirst("Gene");
		queue.addFirst("Elizabeth");
		queue.addFirst("Clara");
		System.out.println(queue);
		queue.removeLast();
		queue.removeLast();
		System.out.println(queue);

		List list1 = new ArrayList();
		list1.add("One");
		list1.add("Two");
		list1.add("Three");
		System.out.println(list1);
		ListIterator li = list1.listIterator();
		li.next();
		li.remove();
	}
}
