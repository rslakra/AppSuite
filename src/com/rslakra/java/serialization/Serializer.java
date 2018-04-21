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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;

/**
 * This class defines utility routines that use Java serialization.
 **/
public class Serializer {
	/**
	 * Serialize the object o (and any Serializable objects it refers to) and
	 * store its serialized state in File f.
	 **/
	static void store(Serializable o, File f) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		// This method serializes an object graph
		out.writeObject(o);
		out.close();
	}
	
	/**
	 * Deserialize the contents of File f and return the resulting object
	 **/
	static Object load(File f) throws IOException, ClassNotFoundException {
		// The class for de-serialization
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		// This method deserializes an object graph
		return in.readObject();
	}
	
	/**
	 * Use object serialization to make a "deep clone" of the object o.
	 * This method serializes o and all objects it refers to, and then
	 * deserializes that graph of objects, which means that everything is
	 * copied. This differs from the clone() method of an object which is
	 * usually implemented to produce a "shallow" clone that copies references
	 * to other objects, instead of copying all referenced objects.
	 **/
	static Object deepClone(final Serializable o) throws IOException, ClassNotFoundException {
		// Create a connected pair of "piped" streams.
		// We'll write bytes to one, and them from the other one.
		final PipedOutputStream pipeout = new PipedOutputStream();
		PipedInputStream pipein = new PipedInputStream(pipeout);
		
		// Now define an independent thread to serialize the object and write
		// its bytes to the PipedOutputStream
		Thread writer = new Thread() {
			public void run() {
				ObjectOutputStream out = null;
				try {
					out = new ObjectOutputStream(pipeout);
					out.writeObject(o);
				} catch (IOException e) {
				} finally {
					try {
						out.close();
					} catch (Exception e) {
					}
				}
			}
		};
		writer.start(); // Make the thread start serializing and writing
		
		// Meanwhile, in this thread, read and deserialize from the piped
		// input stream. The resulting object is a deep clone of the original.
		ObjectInputStream in = new ObjectInputStream(pipein);
		return in.readObject();
	}
	
	/**
	 * This is a simple serializable data structure that we use below for
	 * testing the methods above
	 **/
	public static class DataStructure implements Serializable {
		String message;
		int[] data;
		DataStructure other;
		
		public String toString() {
			String s = message;
			for (int i = 0; i < data.length; i++)
				s += " " + data[i];
			if (other != null)
				s += "\n\t" + other.toString();
			return s;
		}
	}
	
	/** This class defines a main() method for testing */
	public static class Test {
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			// Create a simple object graph
			DataStructure ds = new DataStructure();
			ds.message = "hello world";
			ds.data = new int[] { 1, 2, 3, 4 };
			ds.other = new DataStructure();
			ds.other.message = "nested structure";
			ds.other.data = new int[] { 9, 8, 7 };
			
			// Display the original object graph
			System.out.println("Original data structure: " + ds);
			
			// Output it to a file
			File f = new File("datastructure.ser");
			System.out.println("Storing to a file...");
			Serializer.store(ds, f);
			
			// Read it back from the file, and display it again
			ds = (DataStructure) Serializer.load(f);
			System.out.println("Read from the file: " + ds);
			
			// Create a deep clone and display that. After making the copy
			// modify the original to prove that the clone is "deep".
			DataStructure ds2 = (DataStructure) Serializer.deepClone(ds);
			ds.other.message = null;
			ds.other.data = null; // Change original
			System.out.println("Deep clone: " + ds2);
		}
	}
}
