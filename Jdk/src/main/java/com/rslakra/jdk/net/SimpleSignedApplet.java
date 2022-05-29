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
package com.rslakra.jdk.net;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This class creates an applet which creates a file. When a user clicks on the
 * 'Create File' button, the method actionPerformed calls createFile method. The
 * createFile method creates a text file 'Test.txt' on the c: drive of the
 * user's machine and prints a message in this file. The applet then displays
 * the message for successful creation of the file. If any failure occurs, then
 * a failure message is also displayed.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-24 03:38:57 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class SimpleSignedApplet extends Applet implements ActionListener {
	String message;
	Button createFile;

	public void init() {
		message = new String();
		createFile = new Button("Create File");
		add(createFile);
		createFile.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		String str = ae.getActionCommand();
		if (str.equals("Create File")) {
			createFile("Test.txt");
			repaint();
		}
	}

	public void createFile(String fileName) {
		String path = System.getProperty("user.dir") + "/" + fileName;
		System.out.println("path : " + path);
		try {
			// Create a SampleDigitalCertificate file
			FileOutputStream fileOutputStream = new FileOutputStream(path, true);
			BufferedWriter out = new BufferedWriter(new PrintWriter(fileOutputStream));
			String fileMsg = "Testing the Simple singed applet!!";
			out.write(fileMsg, 0, fileMsg.length());
			out.newLine();
			out.flush();
			fileOutputStream.close();
			// message
			message = "File Created Successfully on disk at : " + path;
		} catch (Exception ex) {
			message = "Exception!! Disk file couldn't be created.";
			message = "Exception while creating file : " + path;
		}
	}

	public void paint(Graphics g) {
		g.drawString(message, 50, 100);
	}
}
