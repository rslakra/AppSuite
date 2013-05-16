package com.devamatre.core.lang.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * TODO: Enter description here ...
 * 
 * Filters files those starts with "txt"
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
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