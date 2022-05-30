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
package com.rslakra.jdk.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.rslakra.core.utils.BeanUtils;
import com.rslakra.jdk.bos.Name;
import com.rslakra.jdk8.Person;

/**
 * @author Rohtash Singh Lakra
 */
public class ReflectionExample {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Person person = new Person("Rohtash Lakra");
		Method[] methods = person.getClass().getMethods();
		System.out.println("methods:" + methods);
		System.out.println();

		try {
			Method method = person.getClass().getMethod("getName");
			System.out.println(method.getName() + " is getter:" + BeanUtils.isGetter(method));

			method = person.getClass().getMethod("setName", String.class);
			System.out.println(method.getName() + " is setter:" + BeanUtils.isSetter(method));

			method = person.getClass().getMethod("isAdult");
			System.out.println(method.getName() + " is getter:" + BeanUtils.isGetter(method));

			// Read private field 'middleName' by reflection
			Name name = new Name("Rohtash", "Lakra");
			Field middleNameField = Name.class.getDeclaredField("middleName");
			middleNameField.setAccessible(true);
			String middleName = (String) middleNameField.get(name);
			System.out.println(middleName);
			// set value of the private field
			middleNameField.set(name, "Singh");

			// set middle name by reflection
			Method setMiddleNameMethod = Name.class.getDeclaredMethod("setMiddleName", String.class);
			setMiddleNameMethod.setAccessible(true);
			setMiddleNameMethod.invoke(name, "Singh");

			// get middle name by reflection method
			Method getMiddleNameMethod = Name.class.getDeclaredMethod("getMiddleName", null);
			getMiddleNameMethod.setAccessible(true);
			middleName = (String) getMiddleNameMethod.invoke(name, null);
			System.out.println(middleName);

		} catch (NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}

}
