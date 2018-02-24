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
package com.rslakra.java.vm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import sun.misc.VMSupport;

public class MemoryManager {

	private static MemoryManager instance;

	/** numFmt */
	private NumberFormat objNumberFormat;

	private ArrayList fakes = new ArrayList();
	private int reserveMemory;
	private int releaseMemory;
	private boolean runningCEEJ;
	Properties agentProperties;

	public MemoryManager() {
		objNumberFormat = NumberFormat.getInstance();
		objNumberFormat.setGroupingUsed(true);
		String vmName = System.getProperty("java.vm.name");
		System.out.println("Virtual Machine : " + vmName);
		runningCEEJ = "Siege CEE-J".equals(vmName);
		agentProperties = VMSupport.getAgentProperties();
	}

	public static MemoryManager getInstance() {
		if (instance == null) {
			instance = new MemoryManager();
		}
		return instance;
	}

	/**
	 * This is just for testing purposes. don't remove it. To ignore this time
	 * consuming process, in Config.properties file set the property
	 * "reserve.memory" to X (in MB).
	 */
	public void reserveMemory() {
		int memToReserve = (getReserveMemory() * 1024 * 1024);
		System.out.println("Reserving Total Memery : " + memToReserve + " bytes \n");

		// print memory info
		logMemory();

		// reserve memory
		for (int ctr = 0, size = (memToReserve / 18); ctr < size; ++ctr) {
			fakes.add(new Fake());
			logMemory();
		}

		// print memory info
		logMemory();
	}

	/**
	 * release the reserved memory.
	 */
	public void releaseMemory() {
		// print memory info
		logMemory();

		System.out.println("Releasing Memery ...");
		// clear memory
		for (int ctr = 0, listSize = fakes.size(); ctr < listSize; ++ctr) {
			fakes.set(ctr, null);
		}

		fakes.clear();
		fakes = null;

		// print memory info
		logMemory();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printArgs(args);
		MemoryManager memMgr = MemoryManager.getInstance();
		if (args != null && args.length > 0) {
			try {
				if (args[0].equals("-reserve.memory") || args[0].equals("-res")) {
					try {
						memMgr.setReserveMemory(Integer.parseInt(args[1]));
					} catch (Exception ex) {
						memMgr.setReserveMemory(0);
					}
					memMgr.reserveMemory();
				} else {
					usageReserveMemory();
					System.exit(1);
				}
			} catch (Exception ex) {
				usageReserveMemory();
				System.exit(1);
			}
		}
	}

	/**
	 * Logs memory status.
	 */
	public void logMemory() {
		Runtime rt = Runtime.getRuntime();
		long totalMem = rt.totalMemory();
		long availMem = rt.freeMemory();
		long usedMem = totalMem - availMem;
		StringBuffer msg = new StringBuffer("total mem: ");
		msg.append(objNumberFormat.format(totalMem));
		msg.append(", used mem: ");
		msg.append(objNumberFormat.format(usedMem));
		msg.append(", avail mem: ");
		msg.append(objNumberFormat.format(availMem));
		// check siege is running or not
		if (runningCEEJ) {
			long mallocHeap = Integer.parseInt(agentProperties.getProperty("malloc_heap"));
			msg.append(", CEEJ Malloc: ");
			msg.append(objNumberFormat.format(mallocHeap));
			msg.append(", LFB: ");
			msg.append(objNumberFormat.format(agentProperties.getProperty("largest_free_block")));
			long maxmem = Integer.parseInt(agentProperties.getProperty("malloc_watermark_max"));
			long maxtime = Integer.parseInt(agentProperties.getProperty("malloc_watermark_timestamp"));

			if (maxmem != 0) {
				msg.append(", HighMark=");
				msg.append(objNumberFormat.format(maxmem));
				msg.append(", HighTime=");
				msg.append(new Date(maxtime));
			}
		}

		System.out.println(msg);
	}

	public int getReserveMemory() {
		return reserveMemory;
	}

	/**
	 * @param reserveMemory
	 *            if this value is zero, use default value which is 20.
	 */
	public void setReserveMemory(int reserveMemory) {
		this.reserveMemory = (reserveMemory == 0) ? 20 : reserveMemory;
	}

	public int getReleaseMemory() {
		return releaseMemory;
	}

	public void setReleaseMemory(int releaseMemory) {
		this.releaseMemory = (releaseMemory == 0) ? 0 : releaseMemory;
	}

	/**
	 * DOCUMENT ME!
	 */
	private static void usage() {
		usageReserveMemory();
		System.out.println("\tOR\n");
		usageReleaseMemory();
	}

	/**
	 * Prints usage of command.
	 */
	private static void usageReserveMemory() {
		System.out.println("\nUsage: <prog> [-res or -reserve.memory <amount> (default=20MB)]");
		System.out.println("");
	}

	/**
	 * Prints usage of command.
	 */
	private static void usageReleaseMemory() {
		System.out.println("Usage: <prog> [-release]");
		System.out.println("\t[-rel or -release.memory <amount> (default=0)]");
		System.out.println("");
	}

	private static void printArgs(String[] strArgs) {
		StringBuffer strBuffer = new StringBuffer();
		boolean DEBUG = false;
		if (strArgs != null && strArgs.length > 0) {
			strBuffer.append("[");
			for (int i = 0; i < strArgs.length; i++) {
				if (strArgs[i].equals("-debug")) {
					DEBUG = true;
					continue;
				}
				strBuffer.append(strArgs[i]);
				if (i < strArgs.length - 1) {
					strBuffer.append(", ");
				}
			}
			strBuffer.append("]");
		}
		if (strBuffer.length() == 0) {
			usageReserveMemory();
		} else {
			if (DEBUG) {
				System.out.println(
						"args : " + strBuffer.toString() + ", args.length : " + (strArgs != null ? strArgs.length : 0));
			}
		}
	}

	private class Fake {
	}
}