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
package com.rslakra.java.enums;

/**
 * This enum contains the details of file types in alphabetical order.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @version 1.0
 * @since May 25, 2011 11:19:42 AM
 */
public enum FileType {
	/* AVI */
	AVI(".avi"),
	/* BMP */
	BMP(".bmp"),
	/* CLASS */
	CLASS(".class"),
	/* BMP */
	CLASSPATH(".classpath"),
	/* BMP */
	CONF(".conf"),
	/* BMP */
	CSS(".css"),
	/* BMP */
	DOC(".doc"),
	/* BMP */
	GIF(".gif"),
	/* BMP */
	HTM(".htm"),
	/* BMP */
	HTML(".html"),
	/* BMP */
	JAVA(".java"),
	/* BMP */
	JPEG(".jpeg"),
	/* BMP */
	JPG(".jpg"),
	/* BMP */
	LOG(".log"),
	/* BMP */
	MP3(".mp3"),
	/* BMP */
	PROJECT(".project"),
	/* BMP */
	PNG(".png"),
	/* BMP */
	PROPERTIES(".properties"),
	/* BMP */
	TXT(".txt"),
	/* BMP */
	XLS(".xls"),
	/* BMP */
	XML(".xml"),
	/* BMP */
	WAV(".wav"), ;

	/**
	 * type of file.
	 */
	private String fileType;

	/**
	 * 
	 * @param fileType
	 */
	private FileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 
	 * @return
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return getFileType();
	}

}