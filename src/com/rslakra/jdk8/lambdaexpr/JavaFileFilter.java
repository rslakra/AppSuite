/******************************************************************************
 * Copyright (C) Devamatre Inc 2008. All rights reserved.
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
package com.rslakra.jdk8.lambdaexpr;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {
	
	public static void printFiles(File[] files) {
		for(File file : files) {
			System.out.println(file);
		}
	}
	
	public static void main(String[] args) {
		final String dirPath = System.getProperty("user.dir") + "/src/com/rslakra/jdk18";
		JavaFileFilter fileFilter = new JavaFileFilter();
		File file = new File(dirPath);
		File[] files = file.listFiles(fileFilter);
		printFiles(files);
		System.out.println("\n");
		
		// with lambda expression
		File tempFile = new File(dirPath);
		FileFilter lambdaFilter = (File temp) -> temp.getName().endsWith(".java");
		File[] tempFiles = tempFile.listFiles(lambdaFilter);
		printFiles(tempFiles);
	}
	
	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(".java");
	}
	
}
