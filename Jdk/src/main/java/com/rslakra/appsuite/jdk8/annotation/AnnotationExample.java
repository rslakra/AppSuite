/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
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
package com.rslakra.appsuite.jdk8.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Rohtash Lakra
 */
public class AnnotationExample {

    /**
     * Returns true if the value is even number otherwise false.
     * <p>
     * Java7: Multiple values of the annotation
     *
     * @param value
     * @return
     */
    @TestCases({@TestCase(value = 1, expected = false), @TestCase(value = 2, expected = true)})
    public boolean isEven(Integer value) {
        return (value % 2 == 0);
    }

    /**
     * Java8 way of doing the same thing as in <code>isEven</code> method above.
     *
     * @param value
     * @return
     */
    @TestCase(value = 3, expected = true)
    @TestCase(value = 4, expected = false)
    public boolean isOdd(Integer value) {
        return !isEven(value);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        AnnotationExample aExample = new AnnotationExample();

        try {
            Method[] methods = aExample.getClass().getMethods();
            System.out.println("methods:" + methods);

            Method evenMethod = aExample.getClass().getMethod("isEven", Integer.class);
            System.out.println("evenMethod:" + evenMethod);
            TestCases aTestCases = evenMethod.getAnnotation(TestCases.class);
            System.out.println(aExample.isEven(30));
            Arrays.asList(aTestCases.value()).forEach(tc -> System.out.println(tc.value()));
        } catch (NoSuchMethodException | SecurityException ex) {
            ex.printStackTrace();
        }

        System.out.println();

        try {
            Method oddMethod = aExample.getClass().getMethod("isOdd", new Class[]{Integer.class});
            System.out.println("oddMethod:" + oddMethod);
            TestCases aTestCases = oddMethod.getAnnotation(TestCases.class);
            System.out.println(aExample.isOdd(30));
            Arrays.asList(aTestCases.value()).forEach(tc -> System.out.println(tc.value()));
        } catch (NoSuchMethodException | SecurityException ex) {
            ex.printStackTrace();
        }

    }

}
