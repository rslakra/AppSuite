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
package com.rslakra.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-24 08:19:22 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class PredicateExample {

	enum Module {
		BRIEFCASE("BRIEFCASE"), CALENDAR("CALENDAR"), DOCUMENTS("DOCUMENTS"), FAVORITES("FAVORITES"), INBOX(
				"INBOX"), TASK_MANAGEMENT("TASK_MANAGEMENT"), EMAIL("EMAIL"),;

		private String name;

		Module(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public static List<Module> filterModules(List<Module> modules) {
		Predicate<Module> inboxModule = module -> Module.INBOX.getName().equals(module.getName());
		Predicate<Module> emailModule = module -> Module.EMAIL.getName().equals(module.getName());
		if (modules.stream().anyMatch(inboxModule) && modules.stream().anyMatch(emailModule)) {
			modules = modules.stream().filter(emailModule.negate()).collect(Collectors.<Module>toList());
		}

		printModules(modules);
		return modules;
	}

	/**
	 * 
	 * @param modules
	 */
	public static void printModules(List<Module> modules) {
		modules.stream().forEach(System.out::println);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Module> allModules = Arrays.asList(new Module[] { Module.BRIEFCASE, Module.CALENDAR, Module.DOCUMENTS,
				Module.FAVORITES, Module.INBOX, Module.TASK_MANAGEMENT, Module.EMAIL });
		List<Module> inboxModules = Arrays.asList(
				new Module[] { Module.BRIEFCASE, Module.CALENDAR, Module.DOCUMENTS, Module.FAVORITES, Module.INBOX });
		List<Module> eMailModules = Arrays.asList(
				new Module[] { Module.BRIEFCASE, Module.CALENDAR, Module.DOCUMENTS, Module.FAVORITES, Module.EMAIL });
		printModules(allModules);

		// Predicate<Module> inboxModule = module ->
		// Module.INBOX.getName().equals(module.getName()) &&
		// Module.EMAIL.getName().equals(module.getName());
		// Predicate<Module> eMailModule = module ->
		// Module.INBOX.getName().equals(module.getName()) &&
		// Module.EMAIL.getName().equals(module.getName());

		// Predicate<String> bothExists = (list, inboxModule, eMailModule) -> {
		// return list.s .gep.equalsIgnoreCase("apple");
		// };

		System.out.println("============= Filtered Modules ==============");
		filterModules(allModules);
		// allModules =
		// allModules.stream().filter(eMailModule.and(inboxModule).negate()).collect(Collectors.<Module>
		// toList());

		Predicate<String> lengthGreaterThan20 = (str) -> str.length() > 20;
		Predicate<String> lengthLessThan10 = (str) -> str.length() < 10;
		Predicate<String> checkLength = lengthLessThan10.and(lengthGreaterThan20);
		System.out.println(checkLength);

	}

}
