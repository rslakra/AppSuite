package com.rslakra.algorithms.disks;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 02/02/2018 01:24:01 PM
 */
public class DiskFile {
	
	private final String name;
	private final int size;
	
	/**
	 * 
	 * @param name
	 * @param size
	 */
	public DiskFile(final String name, final int size) {
		this.name = name;
		this.size = size;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return getName() + " " + getSize();
	}
	
}
