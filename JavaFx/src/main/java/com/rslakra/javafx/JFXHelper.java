/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, 
 * 	  this list of conditions and the following disclaimer.
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
package com.rslakra.javafx;

import javafx.stage.Stage;

/**
 * @author Rohtash Singh Lakra
 * @date 02/16/2018 01:00:40 PM
 */
public final class JFXHelper {
	
	/** WIDTH */
	public static final double WIDTH = 640;
	
	/** HEIGHT */
	public static final double HEIGHT = 480;
	
	private static JFXHelper instance;
	
	/**
	 * 
	 */
	private JFXHelper() {
	}
	
	/**
	 * 
	 * @return
	 */
	public static JFXHelper getInstance() {
		if (instance == null) {
			synchronized (JFXHelper.class) {
				if (instance == null) {
					instance = new JFXHelper();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * Sets the width and height of the stage.
	 * 
	 * @param stage
	 * @param width
	 * @param height
	 */
	public static void setSize(final Stage stage, final double width, final double height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}
	
	/**
	 * 
	 * @param stage
	 */
	public static void setDefaultSize(final Stage stage) {
		setSize(stage, WIDTH, HEIGHT);
	}
	
}
