package com.apparatus.swing.mdi;

import javax.swing.DefaultDesktopManager;
import javax.swing.JInternalFrame;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 */
public class DDesktopManager extends DefaultDesktopManager {
	/** serialVersionUID */
	private static final long serialVersionUID = -4950393202878168901L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultDesktopManager#activateFrame(javax.swing.JInternalFrame)
	 */
	public void activateFrame(JInternalFrame frame) {
		super.activateFrame(frame);
		System.out.println(frame);
	}
}
