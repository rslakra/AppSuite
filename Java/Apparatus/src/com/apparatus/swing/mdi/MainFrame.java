package com.apparatus.swing.mdi;

import java.awt.BorderLayout;

import javax.swing.DefaultDesktopManager;
import javax.swing.DesktopManager;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * You'll need a frame which keeps all internal frames. it could be an applet
 * too.
 */
public class MainFrame extends JFrame {
	protected JDesktopPane desktopPane;
	protected DesktopManager desktopManager;

	// constructor of the application
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 300);

		// some initializations here...
		desktopPane = new JDesktopPane();
		// desktopPane.setOpaque(false);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopManager = desktopPane.getDesktopManager();
		if (desktopManager == null) {
			desktopManager = new DefaultDesktopManager();
			desktopPane.setDesktopManager(desktopManager);
		}

		// add all internal frames, you need at startup
		desktopPane.add(new InternalFrame());

		setVisible(true);
	}

	// adds an internal frame to desktop
	public void addInternalFrame(JInternalFrame frame) {
		desktopManager.activateFrame(frame);
	}

	public static void main(String args[]) {
		new MainFrame();
	}
}

//
// Now you have to implement the several internal frames
//
class InternalFrame extends JInternalFrame {
	public InternalFrame() {
		// call the father, see API for several constructor-paramaters
		super("Internal Frame", true, true, true, true);

		// just add one button to the frame
		getContentPane().add(new JButton("Hello"), BorderLayout.CENTER);

		// that's it.
		setVisible(true);
	}
}