package com.rslakra.algorithms.disks;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 02/02/2018 01:26:16 PM
 */
public class Disk {
	
	private final String name;
	private final int size;
	private int freeSize;
	
	/**
	 * 
	 * @param name
	 * @param size
	 */
	public Disk(final String name, final int size) {
		this.name = name;
		this.size = size;
		this.freeSize = this.size;
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
	 * @return
	 */
	public int getFreeSize() {
		return this.freeSize;
	}
	
	/**
	 * Returns true if the disk has enough space of the given size.
	 * 
	 * @param size
	 * @return
	 */
	public boolean hasSpace(int size) {
		return getFreeSize() >= size;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "Disk<" + getName() + ", " + getSize() + ", " + getFreeSize() + ">";
	}
	
	/**
	 * 
	 * @param diskFile
	 */
	public void storeDiskFile(DiskFile diskFile) {
		if(hasSpace(diskFile.getSize())) {
			System.out.println(diskFile + " saved at " + getName() + " disk.");
			this.freeSize -= diskFile.getSize();
		} else {
			System.out.println("Disk " + getName() + " does not have enough space.");
		}
	}
}
