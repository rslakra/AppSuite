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
package com.rslakra.java.xml;

/**
 * 
 * @author Rohtash Singh(rohtash.singh@gmail.com)
 * 
 *         XML Format <?xml version="1.0"?> <Actions>
 *         <Action Id="getConfigDetails"> <Name>userAction</Name>
 *         <Class>User</Class> <Method>getConfig</Method> </Action> </Actions>
 */
public class Action {

	private String id;
	private String name;
	private String klass;
	private String method;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String toString() {
		return "Action [Id:" + getId() + ", Name:" + getName() + ", Class:" + getKlass() + ", Method:" + getMethod()
				+ "]";
	}

	public String toXmlString() {
		StringBuffer xmlString = new StringBuffer();
		XMLHelper xmlUtils = XMLHelper.getInstance();
		xmlString.append(xmlUtils.startElement("Action", null, null));
		xmlString.append(xmlUtils.generateElement("Id", getId()));
		xmlString.append(xmlUtils.generateElement("Name", getName()));
		xmlString.append(xmlUtils.generateElement("Class", getKlass()));
		xmlString.append(xmlUtils.generateElement("Method", getMethod()));
		xmlString.append(xmlUtils.endElement("Action"));
		return xmlString.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Action action = new Action();
		action.setId("getId");
		action.setName("action");
		action.setKlass("Action");
		action.setMethod("getMethod");

		System.out.println(action);
		System.out.println(action.toXmlString());
	}
}
