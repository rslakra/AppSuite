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
		if (hasSpace(diskFile.getSize())) {
			System.out.println(diskFile + " saved at " + getName() + " disk.");
			this.freeSize -= diskFile.getSize();
		} else {
			System.out.println("Disk " + getName() + " does not have enough space.");
		}
	}
}
