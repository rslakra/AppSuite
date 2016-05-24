package com.rslakra;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyFrame extends JFrame {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor.
	 * 
	 * @param arguments
	 */
	public MyFrame(List<String> arguments) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("My BVTest Frame");
		setSize(300, 200);
		setLocation(10, 200);
		String msg = "BVTestApp - Java Version:" + System.getProperty("java.version");
		arguments.add(0, msg);
		
		JTextArea jTextArea = new JTextArea(arguments.toString());
		jTextArea.setEditable(false);
		
		// add component
		add(jTextArea);
	}
}
