/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, 
 * 	  this list of conditions and the following disclaimer.
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
package com.rslakra.java.i18n;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A convenience class that can display a localized exception message depending
 * on the class of the exception. It uses a MessageFormat, and passes five
 * arguments that the localized message may include: {0}: the message included
 * in the exception or error. {1}: the full class name of the exception or
 * error. {2}: a guess at what file the exception was caused by. {3}: a line
 * number in that file. {4}: the current date and time. Messages are looked up
 * in a ResourceBundle with the basename "Errors", using a the full class name
 * of the exception object as the resource name. If no resource is found for a
 * given exception class, the superclasses are checked.
 **/
public class LocalizedError {
	public static void display(Throwable error) {
		ResourceBundle bundle;
		// Try to get the resource bundle.
		// If none, print the error in a non-localized way.
		try {
			bundle = ResourceBundle.getBundle("Errors");
		} catch (MissingResourceException e) {
			error.printStackTrace(System.err);
			return;
		}
		
		// Look up a localized message resource in that bundle, using the
		// classname of the error (or its superclasses) as the resource name.
		// If no resource was found, display the error without localization.
		String message = null;
		Class c = error.getClass();
		while ((message == null) && (c != Object.class)) {
			try {
				message = bundle.getString(c.getName());
			} catch (MissingResourceException e) {
				c = c.getSuperclass();
			}
		}
		if (message == null) {
			error.printStackTrace(System.err);
			return;
		}
		
		// Try to figure out the filename and line number of the
		// exception. Output the error's stack trace into a string, and
		// use the heuristic that the first line number that appears in
		// the stack trace is after the first or second colon. We assume that
		// this stack frame is the first one the programmer has any control
		// over, and so report it as the location of the exception.
		// Note that this is implementation-dependent and not robust...
		String filename = "";
		int linenum = 0;
		try {
			StringWriter sw = new StringWriter(); // Output stream to a string.
			PrintWriter out = new PrintWriter(sw); // PrintWriter wrapper.
			error.printStackTrace(out); // Print stacktrace.
			String trace = sw.toString(); // Get it as a string.
			int pos = trace.indexOf(':'); // Look for first colon.
			if (error.getMessage() != null) // If the error has a message
				pos = trace.indexOf(':', pos + 1); // look for second colon.
			int pos2 = trace.indexOf(')', pos); // Look for end of line #
			linenum = Integer.parseInt(trace.substring(pos + 1, pos2)); // line
																		// #
			pos2 = trace.lastIndexOf('(', pos); // Back to start of filename.
			filename = trace.substring(pos2 + 1, pos); // Get filename.
		} catch (Exception e) {
			;
		} // Ignore exceptions.
		
		// Set up an array of arguments to use with the message
		String errmsg = error.getMessage();
		Object[] args = { ((errmsg != null) ? errmsg : ""), error.getClass().getName(), filename, new Integer(linenum), new Date() };
		// Finally, display the localized error message, using
		// MessageFormat.format() to substitute the arguments into the message.
		System.out.println(MessageFormat.format(message, args));
	}
	
	/**
	 * This is a simple test program that demonstrates the display() method. You
	 * can use it to generate and display a FileNotFoundException or an
	 * ArrayIndexOutOfBoundsException
	 **/
	public static void main(String[] args) {
		try {
			FileReader in = new FileReader(args[0]);
		} catch (Exception e) {
			LocalizedError.display(e);
		}
	}
}
