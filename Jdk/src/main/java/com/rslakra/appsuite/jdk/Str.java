/*******************************************************************************
 * Copyright (C) Devamatre 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code or the snippet
 * is not permitted without prior express written consent of Devamatre.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 *
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 *
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *******************************************************************************/
package com.rslakra.appsuite.jdk;

/**
 * How to create your own String class.
 *
 * @author Rohtash Lakra
 * @date 01/26/2017 02:41:09 PM
 */
public final class Str implements CharSequence {

    private final char[] value;
    private final int offset;
    private final int count;

    public Str(String str) {
        this(0, str.length(), str.toCharArray());
    }

    /**
     * @param offset
     * @param count
     * @param value
     */
    public Str(int offset, int count, char[] value) {
        this.value = value;
        this.offset = offset;
        this.count = count;
    }

    /**
     * @return
     */
    @Override
    public int length() {
        return count;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public char charAt(int index) {
        return value[index + offset];
    }

    /**
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    @Override
    public CharSequence subSequence(int beginIndex, int endIndex) {
        return new Str(offset + beginIndex, endIndex - beginIndex, value);
    }

    /**
     * Returns the sub-string.
     *
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public Str substring(int beginIndex, int endIndex) {
        // check boundary
        return new Str(offset + beginIndex, endIndex - beginIndex, value);
    }

    /**
     * @return
     */
    private char[] subString() {
        char[] subStr = new char[offset + count];
        int ctr = 0;
        for (int i = offset; i < subStr.length; i++) {
            subStr[ctr++] = value[i];
        }
        return subStr;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new String(subString());
    }

    /**
     * Testing.
     *
     * @param args
     */
    public static void main(String[] args) {
        Str str = new Str("Rohtash Singh");
        System.out.println("str:" + str);
        Str firstName = str.substring(0, 7);
        System.out.println("firstName:" + firstName);
        Str lastName = str.substring(8, str.length());
        System.out.println("lastName:" + lastName);
    }

}
