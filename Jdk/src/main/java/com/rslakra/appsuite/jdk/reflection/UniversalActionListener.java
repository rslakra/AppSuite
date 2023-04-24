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

// Only used for the test program below.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UniversalActionListener implements ActionListener {
    protected Object target;
    protected Object argument;
    protected Method method;

    public UniversalActionListener(Object target, String methodname, Object arg)
            throws NoSuchMethodException, SecurityException {
        this.target = target; // Save the target object.
        this.argument = arg; // And method argument.

        /* Now look up and Save the Method to invoke on that target object. */
        Class<?> targetClassType;
        Class<?>[] parameters;
        targetClassType = target.getClass(); // The Class object.
        if (arg == null) {
            parameters = new Class[0]; // Method parameter.
        } else {
            parameters = new Class[]{arg.getClass()};
        }
        method = targetClassType.getMethod(methodname, parameters); // Find matching method.
    }

    public void actionPerformed(ActionEvent event) {
        Object[] arguments;
        if (argument == null) {
            arguments = new Object[0]; // Set up arguments.
        } else {
            arguments = new Object[]{argument};
        }
        try {
            method.invoke(target, arguments); // And invoke the method.
        } catch (IllegalAccessException e) { // Should never happen.
            System.err.println("IllegalAccessException: " + e);
        } catch (InvocationTargetException e) { // Should never happen.
            System.err.println("InvocationTargetException: " + e);
        }
    }

    /* A simple test program for the UniversalActionListener. */
    public static void main(String[] args) throws NoSuchMethodException {
        Frame f = new Frame("UniversalActionListener Test"); // Create window.
        f.setLayout(new FlowLayout()); // Set layout manager.
        Button tickButton = new Button("tick"); // Create buttons.
        Button tockButton = new Button("tock");
        Button closeButton = new Button("Close Window");
        f.add(tickButton);
        f.add(tockButton);
        f.add(closeButton); // Add them to window.
        // Specify what the buttons do. Invoke a named method with
        // the UniversalActionListener object.
        tickButton.addActionListener(new UniversalActionListener(tickButton, "setLabel", "tock"));
        tickButton.addActionListener(new UniversalActionListener(tockButton, "setLabel", "tick"));
        tickButton.addActionListener(new UniversalActionListener(closeButton, "hide", null));
        tockButton.addActionListener(new UniversalActionListener(tickButton, "setLabel", "tick"));
        tockButton.addActionListener(new UniversalActionListener(tockButton, "setLabel", "tock"));
        tockButton.addActionListener(new UniversalActionListener(closeButton, "show", null));
        closeButton.addActionListener(new UniversalActionListener(f, "dispose", null));
        f.pack(); // Set window size.
        f.show(); // And pop it up.
    }
}
