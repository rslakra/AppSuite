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
import java.io.FilenameFilter;

import com.rslakra.logger.LogManager;
import com.rslakra.logger.Logger;

/**
 * 
 * @author rosingh
 *
 */
public class DirList {

	private final static Logger logger = LogManager.getLogger(DirList.class);

	public DirList() {
	}

	public File[] getFiles(String path) {
		File file = new File(path);
		return file.listFiles();
	}

	public File[] getFiles(String path, FilenameFilter fNameFilter) {
		File file = new File(path);
		return file.listFiles(fNameFilter);
	}

	private void printFileDetails(File f) {
		String fileDetails = "\n" + f.getAbsolutePath() + "\t\t|" + f.getName() + "\t|"
				+ (f.isDirectory() ? "Directory" : (f.isFile() ? "File" : "UnKnown")) + "\t|" + f.canRead() + "\t|"
				+ f.canWrite() + "\t|" + f.length() + " Bytes" + "\t|"
				+ new java.util.Date(f.lastModified()).toString();
		logger.debug(fileDetails);
	}

	public void printList(File[] list) {
		// DebugUtils.printLineWithHeading("File Details", '=', "File
		// Details".length());
		String titles = "\nAbsolutePath \t\t\t|Name \t\t|Type \t\t|Readable \t|Writable \t|Length \t|LastModified";
		logger.debug(titles);
		if (list == null)
			return;
		for (int i = 0; i < list.length; i++) {
			printFileDetails(list[i]);
		}
	}

	public static void main(String[] args) {
		DirList dirList = new DirList();
		//
		String path = null;
		logger.debug("path : " + path);

		File file = new File(path);
		//
		dirList.printList(dirList.getFiles(path));
		/**
		 * using DirFilter
		 */
		path += "\\A*.*";
		logger.debug("path : " + path);
		dirList.printList(dirList.getFiles(path, new DirFilter(path)));
		dirList.printList(dirList.getFiles("C:\\*.*"));

	}
}