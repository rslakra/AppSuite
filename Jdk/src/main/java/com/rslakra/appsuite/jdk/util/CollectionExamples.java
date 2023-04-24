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
package com.rslakra.appsuite.jdk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO Auto-generated comments.
 * 
 * @author rohtash.singh
 * @version May 9, 2006
 * 
 */
public class CollectionExamples {

	List<Name> list = new ArrayList<Name>();

	public void init() {
		Name name = null;
		for (int i = 0; i < 5; i++) {
			name = new Name((i + 1));
			list.add(name);
		}
	}

	public void modify() {
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			// ((Name) iter.next()).setName("Name " );
			Name name = (Name) iter.next();
			name.setName("!11");

		}
		// for (int i = 0; i < list.size(); i++) {
		// Name name = (Name)list.get(i);
		// name.setName("Name " + (i+1));
		// }
	}

	public void show() {
		for (int i = 0; i < list.size(); i++) {
			Name name = (Name) list.get(i);
			System.out.println(name.getName());
		}
		// for (Iterator iter = list.iterator(); iter.hasNext();) {
		//
		// System.out.println( ((Name)iter.next()).getName() );
		//
		// }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CollectionExamples r = new CollectionExamples();
		r.init();
		r.show();

		r.modify();
		r.show();
	}

}

// class Name {
//
// private String name;
//
// public String getName() {
// return name;
// }
//
// public void setName(String name) {
// this.name = name;
// }
// }
