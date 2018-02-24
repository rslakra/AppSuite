/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
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
package com.rslakra.java.profiler;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class ProfilerAgent {
	/**
	 * A reference to the {@link java.lang.instrument.Instrumentation} instance
	 * passed to this agent's {@link #premain} method. This way we can keep
	 * using the Instrumentation functionality!
	 **/
	static private Instrumentation _instrumentation = null;

	/**
	 * This method is called before the application�s main-method is called,
	 * when this agent is specified to the Java VM.
	 **/
	public static void premain(String agentArgs, Instrumentation instrumentation) {
		System.out.println("Rohtash's Agent.premain() was called.");

		// Initialize the static variables we use to track information.
		_instrumentation = instrumentation;

		// Set up the class-file transformer.
		ClassFileTransformer classFileTransformer = new ProfilerTransformer();
		System.out.println("Adding a ProfilerTransformer instance to the JVM.");
		_instrumentation.addTransformer(classFileTransformer);
	}
}