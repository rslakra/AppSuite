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
package com.rslakra.java.profiling;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Profiler {

	/**
	 * 
	 * @param klass
	 * @param object
	 * @return
	 */
	public static Object getProfiled(Class<?> klass, Object object) {
		if (!ProfilingUtil.isActive()) {
			return object;
		}

		if (klass.isInterface()) {
			InvocationHandler invHandler = new ProfilingInvocationHandler(object);
			return Proxy.newProxyInstance(klass.getClassLoader(), new Class[] { klass }, invHandler);
		}

		return object;
	}

	/**
	 * 
	 * @param target
	 * @param value
	 * @param args
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object invoke(Method target, Object value, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		if (!ProfilingUtil.isActive()) {
			return target.invoke(value, args);
		}
		String logLine = new String(getClassName(target) + "." + target.getName() + "()");

		ProfilingUtil.push(logLine);
		try {
			Object result = target.invoke(value, args);

			if ((result != null) && (target.getReturnType().isInterface())) {
				InvocationHandler timerHandler = new ProfilingInvocationHandler(result);
				Object localObject1 = Proxy.newProxyInstance(result.getClass().getClassLoader(),
						new Class[] { target.getReturnType() }, timerHandler);
				return localObject1;
			}

			return result;
		} finally {
			ProfilingUtil.pop(logLine);
		}
		// throw localObject2;
	}

	/**
	 * Returns trimmed class name.
	 * 
	 * @param method
	 * @return
	 */
	public static String getClassName(Method method) {
		String classname = method.getDeclaringClass().getName();
		return classname.substring(classname.lastIndexOf('.') + 1);
	}
}