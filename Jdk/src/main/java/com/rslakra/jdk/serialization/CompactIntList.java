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
package com.rslakra.jdk.serialization;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * This subclass of IntList assumes that most of the integers it contains are
 * less than 32,000. It implements Externalizable so that it can define a
 * compact serialization format that takes advantage of this fact.
 **/
public class CompactIntList extends IntList implements Externalizable {
	/**
	 * This version number is here in case a later revision of this class wants
	 * to modify the externalization format, but still retain compatibility
	 * with externalized objects written by this version
	 **/
	static final byte version = 1;
	
	/**
	 * This method from the Externalizable interface is responsible for saving
	 * the complete state of the object to the specified stream. It can write
	 * anything it wants as long as readExternal() can read it.
	 **/
	public void writeExternal(ObjectOutput out) throws IOException {
		if (data.length > size)
			resize(size); // Compact the array.
			
		out.writeByte(version); // Start with our version number.
		out.writeInt(size); // Output the number of array elements
		for (int i = 0; i < size; i++) { // Now loop through the array
			int n = data[i]; // The array element to write
			if ((n < Short.MAX_VALUE) && (n > Short.MIN_VALUE + 1)) {
				// If n fits in a short and is not Short.MIN_VALUE, then write
				// it out as a short, saving ourselves two bytes
				out.writeShort(n);
			} else {
				// Otherwise write out the special value Short.MIN_VALUE to
				// signal that the number does not fit in a short, and then
				// output the number using a full 4 bytes, for 6 bytes total
				out.writeShort(Short.MIN_VALUE);
				out.writeInt(n);
			}
		}
	}
	
	/**
	 * This Externalizable method is responsible for completely restoring the
	 * state of the object. A no-arg constructor will be called to re-create
	 * the object, and this method must read the state written by
	 * writeExternal() to restore the object's state.
	 **/
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// Start by reading and verifying the version number.
		byte v = in.readByte();
		if (v != version)
			throw new IOException("CompactIntList: unknown version number");
		
		// Read the number of array elements, and make array that big
		int newsize = in.readInt();
		resize(newsize);
		this.size = newsize;
		
		// Now read that many values from the stream
		for (int i = 0; i < newsize; i++) {
			short n = in.readShort();
			if (n != Short.MIN_VALUE)
				data[i] = n;
			else
				data[i] = in.readInt();
		}
	}
	
	/** A main() method to prove that it works */
	public static void main(String[] args) throws Exception {
		CompactIntList list = new CompactIntList();
		for (int i = 0; i < 100; i++)
			list.add((int) (Math.random() * 40000));
		CompactIntList copy = (CompactIntList) Serializer.deepClone(list);
		if (list.equals(copy))
			System.out.println("equal copies");
		Serializer.store(list, new File("compactintlist.ser"));
	}
}
