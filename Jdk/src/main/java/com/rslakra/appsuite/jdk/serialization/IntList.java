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
package com.rslakra.appsuite.jdk.serialization;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A simple class that implements a growable array of ints, and knows
 * how to serialize itself as efficiently as a non-growable array.
 **/
public class IntList implements Serializable {
    protected int[] data = new int[8]; // An array to store the numbers.
    protected transient int size = 0; // Index of next unused element of array

    /**
     * Return an element of the array
     */
    public int get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            return data[index];
        }
    }

    /**
     * Add an int to the array, growing the array if necessary
     */
    public void add(int x) {
        if (data.length == size) {
            resize(data.length * 2); // Grow array if needed.
        }
        data[size++] = x; // Store the int in it.
    }

    /**
     * An internal method to change the allocated size of the array
     */
    protected void resize(int newsize) {
        int[] newdata = new int[newsize]; // Create a new array
        System.arraycopy(data, 0, newdata, 0, size); // Copy array elements.
        data = newdata; // Replace old array
    }

    /**
     * Get rid of unused array elements before serializing the array
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        if (data.length > size) {
            resize(size); // Compact the array.
        }
        out.defaultWriteObject(); // Then write it out normally.
    }

    /**
     * Compute the transient size field after deserializing the array
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Read the array normally.
        size = data.length; // Restore the transient field.
    }

    /**
     * Does this object contain the same values as the object o?
     * We override this Object method so we can test the class.
     **/
    public boolean equals(Object o) {
        if (!(o instanceof IntList)) {
            return false;
        }
        IntList that = (IntList) o;
        if (this.size != that.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.data[i] != that.data[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * A main() method to prove that it works
     */
    public static void main(String[] args) throws Exception {
        IntList list = new IntList();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 40000));
        }
        IntList copy = (IntList) Serializer.deepClone(list);
        if (list.equals(copy)) {
            System.out.println("equal copies");
        }
        Serializer.store(list, new File("intlist.ser"));
    }
}
