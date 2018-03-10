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
package com.rslakra.java.net;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This applet connects to the "finger" server on the host
 * it was served from to determine who is currently logged on.
 * Because it is an untrusted applet, it can only connect to the host
 * from which it came. Since web servers do not usually run finger
 * servers themselves, this applet will often be used in conjunction
 * with a proxy server, to serve it from some other host that does run
 * a finger server.
 **/
public class Who extends Applet implements ActionListener, Runnable {
	Button who; // The button in the applet
	
	/**
	 * The init method just creates a button to display in the applet.
	 * When the user clicks the button, we'll check who is logged on.
	 **/
	public void init() {
		who = new Button("Who?");
		who.setFont(new Font("SansSerif", Font.PLAIN, 14));
		who.addActionListener(this);
		this.add(who);
	}
	
	/**
	 * When the button is clicked, start a thread that will connect to
	 * the finger server and display who is logged on
	 **/
	public void actionPerformed(ActionEvent e) {
		new Thread(this).start();
	}
	
	/**
	 * This is the method that does the networking and displays the results.
	 * It is implemented as the body of a separate thread because it might
	 * take some time to complete, and applet methods need to return promptly.
	 **/
	public void run() {
		// Disable the button so we don't get multiple queries at once...
		who.setEnabled(false);
		
		// Create a window to display the output in
		Frame f = new Frame("Who's Logged On: Connecting...");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				((Frame) e.getSource()).dispose();
			}
		});
		TextArea t = new TextArea(10, 80);
		t.setFont(new Font("MonoSpaced", Font.PLAIN, 10));
		f.add(t, "Center");
		f.pack();
		f.show();
		
		// Find out who's logged on
		Socket s = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			// Connect to port 79 (the standard finger port) on the host
			// that the applet was loaded from.
			String hostname = this.getCodeBase().getHost();
			s = new Socket(hostname, 79);
			// Set up the streams
			out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// Send a blank line to the finger server, telling it that we want
			// a listing of everyone logged on instead of information about an
			// individual user.
			out.print("\n");
			out.flush(); // Send it now!
			
			// Now read the server's response and display it in the textarea
			// The server should send lines terminated with \n. The
			// readLine() method will detect these lines, even when running
			// on a Mac that terminates lines with \r
			String line;
			while ((line = in.readLine()) != null) {
				t.append(line);
				t.append("\n");
			}
			// Update the window title to indicate we're finished
			f.setTitle("Who's Logged On: " + hostname);
		}
		// If something goes wrong, we'll just display the exception message
		catch (IOException e) {
			t.append(e.toString());
			f.setTitle("Who's Logged On: Error");
		}
		// And finally, don't forget to close the streams!
		finally {
			try {
				in.close();
				out.close();
				s.close();
			} catch (Exception e) {
			}
		}
		
		// And enable the button again
		who.setEnabled(true);
	}
}
