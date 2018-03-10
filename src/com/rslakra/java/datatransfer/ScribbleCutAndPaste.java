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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
// Clipboard, Transferable, DataFlavor, etc.
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * This component allows the user to scribble in a window, and to cut and
 * paste scribbles between windows. It stores mouse coordinates in a Scribble
 * object, which is used to draw the scribble, and also to transfer the
 * scribble to and from the clipboard. A JPopupMenu provides access to the
 * cut, copy, and paste commands.
 **/
public class ScribbleCutAndPaste extends JComponent implements ActionListener, ClipboardOwner {
	Stroke linestyle = new BasicStroke(3.0f); // Draw with wide lines
	Scribble scribble = new Scribble(); // Holds our scribble
	Scribble selection; // A copy of the scribble as cut
	JPopupMenu popup; // A menu for cut-and-paste
	
	public ScribbleCutAndPaste() {
		// Create the popup menu.
		String[] labels = new String[] { "Clear", "Cut", "Copy", "Paste" };
		String[] commands = new String[] { "clear", "cut", "copy", "paste" };
		popup = new JPopupMenu(); // Create the menu
		popup.setLabel("Edit");
		for (int i = 0; i < labels.length; i++) {
			JMenuItem mi = new JMenuItem(labels[i]); // Create a menu item
			mi.setActionCommand(commands[i]); // Set its action command
			mi.addActionListener(this); // And its action listener
			popup.add(mi); // Add item to the menu
		}
		// Finally, register the popup menu with the component it appears over
		this.add(popup);
		
		// Add event listeners to do the drawing and handle the popup
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					popup.show((Component) e.getSource(), e.getX(), e.getY());
				else
					scribble.moveto(e.getX(), e.getY()); // start new line
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// If this isn't mouse button 1, ignore it
				if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == 0)
					return;
				scribble.lineto(e.getX(), e.getY()); // Add a line
				repaint();
			}
		});
	}
	
	/**
	 * Draw the component.
	 * This method relies on Scribble which implements Shape.
	 **/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(linestyle); // Specify wide lines
		g2.draw(scribble); // Draw the scribble
	}
	
	/** This is the ActionListener method invoked by the popup menu items */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("clear"))
			clear();
		else if (command.equals("cut"))
			cut();
		else if (command.equals("copy"))
			copy();
		else if (command.equals("paste"))
			paste();
	}
	
	/** Clear the scribble. Invoked by popup menu */
	void clear() {
		scribble = new Scribble(); // Get a new, empty scribble
		repaint(); // And redraw everything.
	}
	
	/**
	 * Make a copy of the current Scribble and put it on the clipboard
	 * We can do this because Scribble implements Transferable
	 * The user invokes this method through the popup menu
	 **/
	public void copy() {
		// Get system clipboard
		Clipboard c = this.getToolkit().getSystemClipboard();
		
		// Make a copy of the Scribble object to put on the clipboard
		selection = (Scribble) scribble.clone();
		
		// Put the copy on the clipboard
		c.setContents(selection, // What to put on the clipboard
						this); // Who to notify when it is no longer there
	}
	
	/**
	 * The cut action is just like the copy action, except that we erase the
	 * current scribble after copying it to the clipboard
	 **/
	public void cut() {
		copy();
		clear();
	}
	
	/**
	 * The user invokes this method through the popup menu.
	 * First, ask for the Transferable contents of the system clipboard.
	 * Then ask that Transferable object for the scribble data it represents.
	 * Try using both data flavors supported by the Scribble class.
	 * If it doesn't work, beep to tell the user it failed.
	 **/
	public void paste() {
		Clipboard c = this.getToolkit().getSystemClipboard(); // Get clipboard
		Transferable t = c.getContents(this); // Get its contents
		
		// Now try to get a Scribble object from the transferrable
		Scribble pastedScribble = null;
		try {
			pastedScribble = (Scribble) t.getTransferData(Scribble.scribbleDataFlavor);
		} catch (Exception e) { // UnsupportedFlavor, NullPointer, etc.
			// If that didn't work, try asking for a string instead.
			try {
				String s = (String) t.getTransferData(DataFlavor.stringFlavor);
				// We got a string, so try converting it to a Scribble
				pastedScribble = Scribble.parse(s);
			} catch (Exception e2) { // UnsupportedFlavor, NumberFormat, etc.
				// If we couldn't get and parse a string, give up
				this.getToolkit().beep(); // Tell the user the paste failed
				return;
			}
		}
		
		// If we get here, we've retrieved a Scribble object from the clipboard
		// Add it to the current scribble, and ask to be redrawn
		scribble.append(pastedScribble);
		repaint();
	}
	
	/**
	 * This method implements the ClipboardOwner interface. We specify a
	 * ClipboardOwner when we copy a Scribble to the clipboard. This method
	 * will be invoked when something else is copied to the clipboard, and
	 * bumps our data off the clipboard. When this method is invoked we no
	 * longer have to maintain our copied Scribble object, since it is no
	 * longer available to be pasted. Often, a component will highlight a
	 * selected object while it is on the clipboard, and will use this method
	 * to un-highlight the object when it is no longer on the clipboard.
	 **/
	public void lostOwnership(Clipboard c, Transferable t) {
		selection = null;
	}
	
	/** A simple main method to test the class. */
	public static void main(String[] args) {
		JFrame frame = new JFrame("ScribbleCutAndPaste");
		ScribbleCutAndPaste s = new ScribbleCutAndPaste();
		frame.getContentPane().add(s, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
