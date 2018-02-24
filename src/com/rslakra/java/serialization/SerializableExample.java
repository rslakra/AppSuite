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
package com.rslakra.java.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * TODO Auto-generated comments.
 * 
 * @author rohtash.singh
 * @version May 18, 2006
 * 
 */
public class SerializableExample {

	public void testInstanceObjest() throws Exception {
		// Serialize output an Instance Object
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("t1.tmp"));
		out.writeObject(new Serialized());
		out.flush();

		// Changed the static and transient static value after storage
		Serialized.si = 10000;
		Serialized.tsi = 100000;

		// Read back Serialized Instance Object
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("t1.tmp"));
		Serialized serialized = (Serialized) in.readObject();
		in.close();

		// Show the results
		System.out.println("Output from testInstanceObjest():\n");

		// instant values are serialized
		System.out.println("Instance str     : " + serialized.str);
		System.out.println("Instance i       : " + serialized.i);

		// static values are not serialized for an instant object!
		// The new value is picked up, not the old ones!
		System.out.println("static staticStr : " + serialized.sstr);
		System.out.println("static si        : " + serialized.si);

		// transient values are not serialized
		System.out.println("transient tStr   : " + serialized.tstr);
		System.out.println("transient ti     : " + serialized.ti);

		// transient static values are not serialized
		// The new value is picked up, not the old ones!
		System.out.println("transient static tStaticStr: " + serialized.tsstr);
		System.out.println("transient static tsi  : " + serialized.tsi);
	}

	// ***********************************************************************************
	public void testClassObjest() throws Exception {
		// Serialize output Class Object
		Class c = Class.forName("org.crocus.corm.core.Serialized");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("t2.tmp"));
		out.writeObject(c);
		out.flush();

		// Changed the static and transient static value after storage
		// Changed the static and transient static value after storage
		Serialized.si = 11111;
		Serialized.tsi = 111111;

		// Read back Serialized Class Object
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("t2.tmp"));
		Class cls = (Class) in.readObject();
		in.close();

		// Show the results
		System.out.println("\nOutput from testClassObjest():\n");

		// Only field with static modifier is legal to get value back
		// The new value is picked up, not the old ones!
		// which means they are both not serialized.
		// The conclusion: static and tansient static does not make any
		// differece

		System.out.println("static sStr  : " + cls.getDeclaredField("staticStr").get(cls));
		System.out.println("static si    : " + cls.getDeclaredField("si").getInt(cls));

		System.out.println("transient static tStaticStr	: " + cls.getDeclaredField("tStaticStr").get(cls));
		System.out.println("transient static tsi  		: " + cls.getDeclaredField("tsi").getInt(cls));

		// all other fields will cause exceptions,uncomment this code and try it
		// out!
		// System.out.println(cls.getDeclaredField("i").get(cls));

		// Obviously the serialzed Class object know all fields in itself
		System.out.println("\n  Print some reflections from Class Serialized:");
		System.out.println("Class 		: " + cls);
		System.out.println("str			: " + cls.getDeclaredField("str"));
		System.out.println("tStr		: " + cls.getDeclaredField("tStr"));
		System.out.println("staticStr	: " + cls.getDeclaredField("staticStr"));
		System.out.println("tStaticStr	: " + cls.getDeclaredField("tStaticStr"));
		System.out.println("Fields		:" + cls.getDeclaredFields());
	}

	public void show(Serialized serialized) {
		System.out.println("===================================================================");
		// instant values are serialized
		System.out.println("Instance str     : " + serialized.str);
		System.out.println("Instance i       : " + serialized.i);

		// static values are not serialized for an instant object!
		// The new value is picked up, not the old ones!
		System.out.println("static staticStr : " + serialized.sstr);
		System.out.println("static si        : " + serialized.si);

		// transient values are not serialized
		System.out.println("transient tStr   : " + serialized.tsi);
		System.out.println("transient ti     : " + serialized.ti);

		// transient static values are not serialized
		// The new value is picked up, not the old ones!
		System.out.println("transient static tStaticStr: " + serialized.tsstr);
		System.out.println("transient static tsi  : " + serialized.tsi);
		System.out.println("===================================================================");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerializableExample serializableExample = new SerializableExample();
		try {
			serializableExample.testInstanceObjest();
			serializableExample.testClassObjest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}