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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestMemory {

	/** numFmt */
	static NumberFormat objNumberFormat;

	static {
		objNumberFormat = NumberFormat.getInstance();
		objNumberFormat.setGroupingUsed(true);
	}

	// static ArrayList fakes = new ArrayList();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logMemory();
		// reserveMemory();
		// reserveStr();
		// logMemory();
		// reserveDate();
		// for(int i =0 ; i < 240; i++) {
		// reserveDate();
		// logMemory();
		// }
		// logMemory();
		reserveDateWithCal();
		logMemory();
	}

	private static void logMemory() {
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
		System.out.println(msg);
	}

	/**
	 * This is just for testing purposes. don't remove it. To ignore this time
	 * consuming process, in config.properties file set the property
	 * "reserve.memory" to 0 (zero).
	 */
	private static void reserveMemory() {
		ArrayList fakes = new ArrayList();
		int size = 20 * 1024 * 1024 / 18;
		for (int ctr = 0; ctr < size; ctr++) {
			fakes.add(new Fake());
		}

		logMemory();
		// mark all objects null for GC
		for (int ctr = 0, listSize = fakes.size(); ctr < listSize; ctr++) {
			fakes.set(ctr, null);
		}

		fakes.clear();
		fakes = null;
	}

	private static class Fake {
	}

	private static void reserveStr() {
		String str = Long.valueOf(System.currentTimeMillis()).toString();
	}

	// static Date date = new Date();
	private static void reserveDate() {
		Date date = new Date(System.currentTimeMillis());
		// System.out.println("date : " + date);
	}

	static Calendar cal = Calendar.getInstance();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");

	private static void reserveDateWithCal() {
		cal.setTimeInMillis(System.currentTimeMillis());
		String mdate = sdf.format(cal.getTime());
		// System.out.println("mdate : " + mdate);
	}

	static StringBuffer sb = new StringBuffer();

	private static void reserveDateWithCalSelfFormatter() {
		cal.setTimeInMillis(System.currentTimeMillis());
		sb.append(cal.get(Calendar.DATE)).append("/").append(cal.get(Calendar.DAY_OF_MONTH)).append("/")
				.append(cal.get(Calendar.DAY_OF_YEAR)).append(" ").append(cal.get(Calendar.HOUR)).append(":")
				.append(cal.get(Calendar.MINUTE)).append(":").append(cal.get(Calendar.SECOND)).append("\n");
		System.out.println(sb.toString());
	}
}