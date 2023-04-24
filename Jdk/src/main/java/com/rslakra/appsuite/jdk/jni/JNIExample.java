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
package com.rslakra.appsuite.jdk.jni;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JNIExample {

    // Native method declaration

    /**
     * This method return sqluare of a number
     */
    native int sqr(int numer);

    /**
     * This method prints Even if the number is even otherwise Odd.
     */
    native void checkEvenOdd(int number);

    // Load the library
    static {
        System.loadLibrary("mynativelib");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        JNIExample jnixample = new JNIExample();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int choice = 0;
        int number;
        do {
            try {
                System.out.println("\t\t\tMini Calculator");
                System.out.println("\t1. Square");
                System.out.println("\t2. Even or Odd");
                System.out.println("\t0. Exit");
                System.out.print("\tEnter Your Choice : ");
                choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("\tEnter Number : ");
                        number = Integer.parseInt(br.readLine());
                        System.out.println("\tSquare of " + number + " is : "
                                           + jnixample.sqr(number));
                        break;
                    case 2:
                        System.out.println("\tEnter Number : ");
                        number = Integer.parseInt(br.readLine());
                        jnixample.checkEvenOdd(number);
                        break;
                    default:
                        System.out.println("\tWrong Choice!, Please Try Again.");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (choice != 0);
    }
}
