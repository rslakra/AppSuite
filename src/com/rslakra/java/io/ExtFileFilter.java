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

/**
 * 
 * TODO: Enter description here ...
 * 
 * Filters files those starts with "txt"
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 * @since 1.0
 * 
 */
public class ExtFileFilter implements FilenameFilter {

	/** extension */
	private String[] extensions;

	/** A flag indicating whether to accept directories or not. */
	private boolean includeDirectories;

	/** ignoreCase */
	private boolean ignoreCase;

	public ExtFileFilter(final String extension) {
		this(extension, true);
	}

	public ExtFileFilter(final String extension,
			final boolean includeDirectories) {
		this(extension, includeDirectories, false);
	}

	public ExtFileFilter(final String extension,
			final boolean includeDirectories, final boolean ignoreCase) {
		this(new String[] { extension }, includeDirectories, ignoreCase);
	}

	public ExtFileFilter(final String[] extensions) {
		this(extensions, true);
	}

	public ExtFileFilter(final String[] extensions,
			final boolean includeDirectories) {
		this(extensions, includeDirectories, false);
	}

	public ExtFileFilter(final String[] extensions,
			final boolean includeDirectories, final boolean ignoreCase) {
		this.extensions = extensions;
		this.includeDirectories = includeDirectories;
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Returns <code>true</code> if the file is accepted, and <code>false</code>
	 * otherwise.
	 * 
	 * @param dir
	 * @param name
	 * @return
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(final File dir, final String name) {
		final File file = new File(dir, name);
		if (includeDirectories && file.isDirectory()) {
			return true;
		}

		for (int i = 0; i < extensions.length; i++) {
			if (ignoreCase
					&& name.toLowerCase().endsWith(extensions[i].toLowerCase())) {
				return true;
			} else if (name.endsWith(extensions[i])) {
				return true;
			}
		}
		return false;
	}
}