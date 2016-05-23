package com.apparatus.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * TODO: Enter description here ...
 * 
 * Filters files those starts with "txt"
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileNameFilter implements FilenameFilter {
	
	/** names */
	private String[] names;
	
	/** A flag indicating whether to accept directories or not. */
	private boolean includeDirectories;
	
	/** ignoreCase */
	private boolean ignoreCase;
	
	public FileNameFilter(final String names) {
		this(names, true);
	}
	
	public FileNameFilter(final String names, final boolean includeDirectories) {
		this(names, includeDirectories, false);
	}
	
	public FileNameFilter(final String names, final boolean includeDirectories, final boolean ignoreCase) {
		this(new String[] { names}, includeDirectories, ignoreCase);
	}
	
	public FileNameFilter(final String[] names) {
		this(names, true);
	}
	
	public FileNameFilter(final String[] names, final boolean includeDirectories) {
		this(names, includeDirectories, false);
	}
	
	public FileNameFilter(final String[] names, final boolean includeDirectories, final boolean ignoreCase) {
		this.names = names;
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
		if(includeDirectories && file.isDirectory()) {
			return true;
		}
		
		for(int i = 0; i < names.length; i++) {
			if(ignoreCase && name.toLowerCase().startsWith(names[i].toLowerCase())) {
				return true;
			} else if(name.startsWith(names[i])) {
				return true;
			}
		}
		return false;
	}
}