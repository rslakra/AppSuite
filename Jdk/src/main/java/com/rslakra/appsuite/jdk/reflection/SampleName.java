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
package com.rslakra.appsuite.jdk.reflection;

import java.awt.*;
import java.lang.reflect.Modifier;

class SampleName {

    public static void main(String[] args) {
        Button b = new Button();
        printName(b);
    }

    public static void printModifiers(Class<?> c) {
        int modifiers = c.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            System.out.println("public");
        }
        if (Modifier.isAbstract(modifiers)) {
            System.out.println("abstract");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.println("final");
        }
        if (Modifier.isNative(modifiers)) {
            System.out.println("native");
        }
        if (Modifier.isPrivate(modifiers)) {
            System.out.println("private");
        }
        if (Modifier.isProtected(modifiers)) {
            System.out.println("protected");
        }
        if (Modifier.isTransient(modifiers)) {
            System.out.println("transient");
        }
    }

    static void printSuperClasses(Class c) {
        Class superclass = c.getSuperclass();
        while (superclass != null) {
            String className = superclass.getName();
            System.out.println(className);
            c = superclass;
            superclass = c.getSuperclass();
        }
    }

    static void printName(Object o) {
        Class c = o.getClass();
        System.out.println("Class Name : " + c.getName());
        System.out.println("Package Name : " + c.getPackage());
        System.out.println("Super Class Name : " + c.getSuperclass());
        System.out.println("Hash Code : " + c.hashCode());
        System.out.println("Is Interface : " + c.isInterface());
        printModifiers(c);
        printSuperClasses(c);
    }
}
