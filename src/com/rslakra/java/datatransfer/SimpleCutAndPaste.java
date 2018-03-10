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
package com.rslakra.java.datatransfer;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This program demonstrates how to add simple copy-and-paste capabilities
 * to an application.
 **/
public class SimpleCutAndPaste extends Frame implements ClipboardOwner {
	/** The main method creates a frame and pops it up. */
	public static void main(String[] args) {
		Frame f = new SimpleCutAndPaste();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.pack();
		f.setVisible(true);
	}
	
	/** The text field that holds the text that is cut or pasted */
	TextField field;
	
	/**
	 * The constructor builds a very simple test GUI, and registers this object
	 * as the ActionListener for the buttons
	 **/
	public SimpleCutAndPaste() {
		super("SimpleCutAndPaste"); // Window title
		this.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Use a big font
		
		// Set up the Cut button
		Button copy = new Button("Copy");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		this.add(copy, "West");
		
		// Set up the Paste button
		Button paste = new Button("Paste");
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paste();
			}
		});
		this.add(paste, "East");
		
		// Set up the text field that they both operate on
		field = new TextField();
		this.add(field, "North");
	}
	
	/**
	 * This method takes the current contents of the text field, creates a
	 * StringSelection object to represent that string, and puts the
	 * StringSelection onto the clipboard
	 **/
	public void copy() {
		// Get the currently displayed value
		String s = field.getText();
		
		// Create a StringSelection object to represent the text.
		// StringSelection is a pre-defined class that implements
		// Transferable and ClipboardOwner for us.
		StringSelection ss = new StringSelection(s);
		
		// Now set the StringSelection object as the contents of the clipboard
		// Also specify that we're the clipboard owner
		this.getToolkit().getSystemClipboard().setContents(ss, this);
		
		// Highlight the text to indicate it is on the clipboard.
		field.selectAll();
	}
	
	/**
	 * Get the contents of the clipboard, and, if we understand the type,
	 * display the contents. This method understands strings and file lists.
	 **/
	public void paste() {
		// Get the clipboard
		Clipboard c = this.getToolkit().getSystemClipboard();
		
		// Get the contents of the clipboard, as a Transferable object
		Transferable t = c.getContents(this);
		
		// Find out what kind of data is on the clipboard
		try {
			if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				// If it is a string, then get and display the string
				String s = (String) t.getTransferData(DataFlavor.stringFlavor);
				field.setText(s);
				
			} else if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				// If it is a list of File objects, get the list and display
				// the name of the first file on the list
				java.util.List files = (java.util.List) t.getTransferData(DataFlavor.javaFileListFlavor);
				java.io.File file = (java.io.File) files.get(0);
				field.setText(file.getName());
			}
		}
		// If anything goes wrong with the transfer, just beep and do nothing.
		catch (Exception e) {
			this.getToolkit().beep();
		}
	}
	
	/**
	 * This method implements the ClipboardOwner interface. It is called when
	 * something else is placed on the clipboard.
	 **/
	public void lostOwnership(Clipboard c, Transferable t) {
		// Un-highlight the text field, since we don't "own" the clipboard
		// anymore, and the text is no longer available to be pasted.
		field.select(0, 0);
	}
}
