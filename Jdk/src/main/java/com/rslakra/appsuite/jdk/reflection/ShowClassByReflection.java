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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * A program that displays a class synopsis for the named class.
 */

public class ShowClassByReflection {

    private static final int BEGINNING = 0; // Root Package Name
    private static final int ENDING = 1; // Class/Interface Name

    /**
     * The main method. Print info about the named class.
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clas = null;
        try {
            clas = Class.forName("reflecton.ShowClassByReflection");
        } catch (ClassNotFoundException ex) {
            System.err.println("\n\tClass Not Found in ClassPath!\n");
            System.err.println(ex);
        }
        System.out.println("\n-----------------------------------------------"
                + "-----------------------------------------------");

        if (clas.isInterface()) {
            System.out.println("\tDetails by Reflection of Interface : '"
                    + getNameOnly(clas.getName(), ".", ShowClassByReflection.ENDING) + "'");
        } else {
            System.out.println("\tDetails by Reflection of Class : '"
                    + getNameOnly(clas.getName(), ".", ShowClassByReflection.ENDING) + "'");
        }
        System.out.println(
                "-----------------------------------------------" + "-----------------------------------------------");
        printClass(clas);
        System.out.println("-----------------------------------------------"
                + "-----------------------------------------------\n");
    }

    /**
     * get Name without package name in beginning.
     *
     * @param string   String
     * @param constant String
     * @param mode     int
     * @return String
     */
    private static String getNameOnly(String string, String constant, int mode) {
        String nameOnly = null;
        int index = -1;
        switch (mode) {
            case ShowClassByReflection.BEGINNING:
                index = string.trim().indexOf(constant);
                nameOnly = string.trim().substring(0, index);
                break;
            case ShowClassByReflection.ENDING:
                index = string.trim().lastIndexOf(constant);
                nameOnly = string.trim().substring((index + 1));
                break;
            default:
                System.err.println("\nMode is Not Correct!\n");
                break;
        }
        return nameOnly;
    }

    /**
     * Display the Modifiers, Name, SuperClass, and Interfaces of a class or
     * Interface. Then go and list all Constructors, Fields, and Methods.
     */
    public static void printClass(Class c) {
        /* Print Modifiers, Type (Class or Interface), Name, and SuperClass. */
        if (c.isInterface()) {
            /* The modifiers will include the "interface" keyword */
            System.out.print(
                    Modifier.toString(c.getModifiers()) + getNameOnly(c.getName(), ".", ShowClassByReflection.ENDING));
        } else {
            System.out.print(Modifier.toString(c.getModifiers()) + " class "
                    + getNameOnly(c.getName(), ".", ShowClassByReflection.ENDING) + " extends "
                    + getNameOnly(c.getSuperclass().getName(), ".", ShowClassByReflection.ENDING));
        }
        /* Print Interfaces or Super-Interfaces of the Class or Interface. */
        Class[] interfaces = c.getInterfaces();
        if ((interfaces != null) && (interfaces.length > 0)) {
            if (c.isInterface()) {
                System.out.println(" extends ");
            } else {
                System.out.print(" implements ");
            }
            for (int i = 0; i < interfaces.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(getNameOnly(interfaces[i].getName(), ".", ShowClassByReflection.ENDING));
            }
        }
        System.out.println(" {"); // Begin class member listing.

        /* Now look up and Display the Members of the Class. */
        System.out.println("/* Constructors */");
        Constructor[] constructors = c.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) { // Display constructors.
            printMethodsConstructors(constructors[i]);
        }

        System.out.println("/* Fields */");
        Field[] fields = c.getDeclaredFields(); // Look up fields.
        for (int i = 0; i < fields.length; i++) { // Display them.
            printFields(fields[i]);
        }

        System.out.println("/* Methods */");
        Method[] methods = c.getDeclaredMethods(); // Look up methods.
        for (int i = 0; i < methods.length; i++) { // Display them.
            printMethodsConstructors(methods[i]);
        }
        System.out.println("}"); // End class member listing.
    }

    /**
     * Return the Name of an Interface or Primitive Type, Handling Arrays.
     */
    public static String typeName(Class t) {
        String brackets = "";
        while (t.isArray()) {
            brackets += "[]";
            t = t.getComponentType();
        }
        return getNameOnly(t.getName(), ".", ShowClassByReflection.ENDING) + brackets;
    }

    /**
     * Return a string version of modifiers, handling spaces nicely.
     */
    public static String modifiers(int m) {
        if (m == 0) {
            return "";
        } else {
            return Modifier.toString(m) + " ";
        }
    }

    /**
     * Print the modifiers, type, and name of a field.
     */
    public static void printFields(Field f) {
        System.out.println("  " + modifiers(f.getModifiers()) + typeName(f.getType()) + " " + f.getName() + ";");
    }

    /**
     * Print the Modifiers, return Type, Name, Parameter Types, and Exception
     * type of a Method or Constructor. Note the use of the Member Interface to
     * allow this method to work with both Method and Constructor objects.
     */
    public static void printMethodsConstructors(Member member) {
        Class returntype = null;
        Class[] parameters;
        Class[] exceptions;

        if (member instanceof Method) {
            Method m = (Method) member;
            returntype = m.getReturnType();
            parameters = m.getParameterTypes();
            exceptions = m.getExceptionTypes();
        } else {
            Constructor c = (Constructor) member;
            parameters = c.getParameterTypes();
            exceptions = c.getExceptionTypes();
        }
        System.out.print(
                "  " + modifiers(member.getModifiers()) + ((returntype != null) ? typeName(returntype) + " " : "")
                        + getNameOnly(member.getName(), ".", ShowClassByReflection.ENDING) + "(");
        for (int i = 0; i < parameters.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(typeName(parameters[i]));
        }
        System.out.print(")");
        if (exceptions.length > 0) {
            System.out.print(" throws ");
        }
        for (int i = 0; i < exceptions.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(typeName(exceptions[i]));
        }
        System.out.println(";");
    }
}
