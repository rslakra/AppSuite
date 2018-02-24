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
package com.rslakra.java.io;

import java.io.File;
import java.io.FileFilter;

/**
 * DefaultFileFilter will filter the files on the basis of passed extension.
 * If the passed extension is null or empty, by default, it will return the
 * ".txt" files as the defaut extension.
 * 
 * @author rosingh
 * Created on Oct 6, 2005
 *
 */
public class DefaultFileFilter implements FileFilter {
	public static final String DEFAULT_EXT = "txt";

	String extension;

	public DefaultFileFilter() {
		this(DEFAULT_EXT);
	}

	public DefaultFileFilter(String extension) {
		if (extension != null && extension.trim().length() > 0) {
			this.extension = extension;
		} else {
			this.extension = DEFAULT_EXT;
		}
		System.err.println("extension : " + this.extension);
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String fileExt = getExtension(f);
		if (fileExt != null && fileExt.equals(extension)) {
			return true;
		}
		return false;
	}

	private String getExtension(File file) {
		String ext = null;
		if (file != null) {
			String fileName = file.getName();
			int index = fileName.lastIndexOf(".");
			if (index > 0 && index < fileName.length() - 1) {
				ext = fileName.substring(index + 1).toLowerCase();
			}
		}
		return ext;
	}

	// The description of this filter
	public String getDescription() {
		// return IOUtility.getFileTypeByExt(extension) + " Files (*." +
		// extension + ")";
		return null;
	}

	public static void main(String[] args) {
		String DEFAULT_FILE_PATH = ".";
		File file = new File(DEFAULT_FILE_PATH);
		DefaultFileFilter fileFilter = new DefaultFileFilter("ipr");
		System.out.println("Description : " + fileFilter.getDescription());
		File[] files = file.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName());
		}
	}
}
