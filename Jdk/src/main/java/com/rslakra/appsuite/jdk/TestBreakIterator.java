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
package com.rslakra.appsuite.jdk;

import java.text.BreakIterator;
import java.util.Locale;

public class TestBreakIterator {

    // Creating and using text boundaries
    public static void main(String[] args) {
        // if (args.length == 1) {
        String stringToExamine = "Hi! Rohtash Here";
        // print each word in order
        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(stringToExamine);
        printEachForward(boundary, stringToExamine);
        // printEachBackward(boundary, stringToExamine);
        // print each sentence in reverse order
        boundary = BreakIterator.getSentenceInstance(Locale.US);
        boundary.setText(stringToExamine);
        printEachBackward(boundary, stringToExamine);
        printFirst(boundary, stringToExamine);
        printLast(boundary, stringToExamine);

        // prints each character in order
        boundary = BreakIterator.getCharacterInstance();
        boundary.setText(stringToExamine);
        printEachForward(boundary, stringToExamine);
        // }
    }

    // Print each element in order
    public static void printEachForward(BreakIterator boundary, String source) {
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
            System.out.println(source.substring(start, end));
        }
    }

    // Print each element in reverse order
    public static void printEachBackward(BreakIterator boundary, String source) {
        int end = boundary.last();
        for (int start = boundary.previous(); start != BreakIterator.DONE; end = start, start = boundary.previous()) {
            System.out.println(source.substring(start, end));
        }
    }

    // Print first element
    public static void printFirst(BreakIterator boundary, String source) {
        int start = boundary.first();
        int end = boundary.next();
        System.out.println(source.substring(start, end));
    }

    // Print last element
    public static void printLast(BreakIterator boundary, String source) {
        int end = boundary.last();
        int start = boundary.previous();
        System.out.println(source.substring(start, end));
    }

    // Print the element at a specified position
    public static void printAt(BreakIterator boundary, int pos, String source) {
        int end = boundary.following(pos);
        int start = boundary.previous();
        System.out.println(source.substring(start, end));
    }
}
