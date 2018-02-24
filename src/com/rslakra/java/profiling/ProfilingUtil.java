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
package com.rslakra.java.profiling;

import java.io.Serializable;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

public class ProfilingUtil implements Serializable {

	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 2748607933739446867L;
	protected static ThreadLocal<Profile> current = new ThreadLocal<Profile>();
	public static final String KEY_ACTIVATE = "lakra.profile.activate";
	public static final String KEY_MIN_TIME = "lakra.profile.mintime";
	private static final Logger LOG = LogManager.getLogger(ProfilingUtil.class);
	private static boolean active = "true".equalsIgnoreCase(System.getProperty(KEY_ACTIVATE));

	public static void push(String name) {
		if (!isActive()) {
			return;
		}

		Profile newTimer = new Profile(name);
		newTimer.setStartTime();

		Profile currentTimer = (Profile) current.get();
		if (currentTimer != null) {
			currentTimer.addChild(newTimer);
		}

		current.set(newTimer);
	}

	public static void pop(String name) {
		if (!isActive()) {
			return;
		}
		Profile currentTimer = (Profile) current.get();
		if ((currentTimer != null) && (name != null) && (name.equals(currentTimer.getResource()))) {
			currentTimer.setEndTime();
			Profile parent = currentTimer.getParent();

			if (parent == null) {
				printTimes(currentTimer);
				current.set(null);
			} else {
				current.set(parent);
			}

		} else if (currentTimer != null) {
			printTimes(currentTimer);
			current.set(null);
			LOG.warn("Unmatched Handler.  Was expecting " + currentTimer.getResource() + ", instead got " + name);
		}
	}

	private static void printTimes(Profile currentTimer) {
		LOG.info(currentTimer.getPrintable(getMinTime()));
	}

	private static long getMinTime() {
		try {
			return Long.parseLong(System.getProperty(KEY_MIN_TIME, "0"));
		} catch (NumberFormatException e) {
		}
		return -1L;
	}

	public static boolean isActive() {
		return active;
	}

	public static void setActive(boolean active) {
		if (active)
			System.setProperty(KEY_ACTIVATE, "true");
		else {
			System.clearProperty(KEY_ACTIVATE);
		}
		ProfilingUtil.active = active;
	}

	public static <T> T profile(String name, ProfilingBlock<T> block) throws Exception {
		push(name);
		try {
			return block.doProfiling();
		} finally {
			pop(name);
		}
		// throw localObject2;
	}

	public static abstract interface ProfilingBlock<T> {
		public abstract T doProfiling() throws Exception;
	}
}