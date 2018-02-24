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
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rohtash Singh
 * @since v1.0
 */
public class Profile implements Serializable {

	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -3150104914363763398L;

	private Profile parent = null;
	private String resource;
	private long startTime;
	private long endTime;
	private List<Profile> children = new ArrayList<Profile>();

	public Profile(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return this.resource;
	}

	public Profile getParent() {
		return this.parent;
	}

	protected void setParent(Profile parent) {
		this.parent = parent;
	}

	public void addChild(Profile child) {
		this.children.add(child);
		child.setParent(this);
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}

	public void setEndTime() {
		this.endTime = (System.currentTimeMillis() - getStartTime());
	}

	public String getPrintable(long minTime) {
		return getPrintable("", minTime);
	}

	protected String getPrintable(String indent, long minTime) {
		if (endTime >= minTime) {
			StringBuilder buffer = new StringBuilder();
			buffer.append(indent);
			buffer.append("[" + endTime + "ms] - " + this.resource);
			buffer.append("\n");

			for (Profile aChildren : this.children) {
				buffer.append(aChildren.getPrintable(indent + "  ", minTime));
			}

			return buffer.toString();
		}

		return "";
	}
}