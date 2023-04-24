/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2019. All rights reserved.
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
package com.rslakra.appsuite.jdk;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @Created Apr 10, 2019 1:04:32 PM
 */
public class Numbers {

    private int value;

    public Numbers() {
    }

    /**
     * @param value
     */
    public Numbers(int value) {
        this.value = value;
    }

    public long[] reverseDigits(long[] digits, int noOfdigits) {
        long[] reverseddigits = new long[noOfdigits];
        for (int i = noOfdigits - 1, j = 0; i >= 0; i--) {
            reverseddigits[j++] = digits[i];
        }
        return reverseddigits;
    }

    public void decimalToBinary(long number) {
        long[] digits = new long[100];
        long[] revdigits = new long[100];
        long origionalNo = number;
        int ctr;
        if (number > 0) {
            ctr = 0;
            while (number != 0) {
                long rem = number % 2;
                digits[ctr++] = rem;
                number = number / 2;
            }
            System.out.print("Decimal : " + origionalNo + " Binary : ");
            revdigits = reverseDigits(digits, ctr);
            for (int i = 0; i < ctr; i++) {
                System.out.print(revdigits[i]);
            }
        } else {
            System.err.println(number + " is not a Positive Number!");
        }
    }

    public char[] getOctals(int digit) {
        byte[] bytes = new byte[10];
        if (digit >= 0 && digit < 8) {
            String[] octals = {"000", "001", "010", "011", "100", "101", "110", "111"};
            bytes = octals[digit].getBytes();
        }
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) bytes[i];
        }
        return chars;
    }

    public void octalToBinary(long octalNumber) {
        long[] octals = new long[100];
        char[] chars = new char[3];

        int ctr = 0;
        chars = getOctals((int) octalNumber);
        for (int i = 0; i < chars.length; i++) {
            System.out.println("" + chars[i]);
        }
    }

    /**
     * Returns the string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * @param number
     * @param other
     */
    public static void swap(Numbers number, Numbers other) {
        Numbers temp = number;
        number = other;
        other = temp;
        System.out.println("number:" + number + ", other:" + other);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Numbers nums = new Numbers();
        long num = 0;
        DataInputStream in = new DataInputStream(System.in);
        System.out.print("Enter a Decimal No:");
        try {
            num = Long.parseLong(in.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Input Type should be Number Only!" + nfe);
        } catch (IOException ex) {
            System.err.println("Entered Number is not Valid!" + ex);
        }
        nums.decimalToBinary(num);
        nums.octalToBinary(num);

        Numbers first = new Numbers(10);
        Numbers second = new Numbers(20);
        System.out.println("first:" + first + ", second:" + second);
        Numbers.swap(first, second);
        System.out.println("first:" + first + ", second:" + second);

    }
}
