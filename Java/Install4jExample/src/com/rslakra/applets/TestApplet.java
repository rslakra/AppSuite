package com.rslakra.applets;

import java.applet.Applet;
import java.awt.Graphics;

public class TestApplet extends Applet {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g) {
		String msg = "Welcome in TestApplet - Java Version:" + System.getProperty("java.version");
		System.out.println(msg);
		
		g.drawString(msg, 40, 20);
	}
	
}