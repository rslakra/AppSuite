package com.apparatus.swing.mdi;

import javax.swing.JInternalFrame;

public class DInternalFrame extends JInternalFrame {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param title
	 */
	public DInternalFrame(String title) {
		this(title, true, false, true, true);
	}

	/**
	 * 
	 * @param title
	 * @param resizable
	 * @param closeable
	 * @param maximizable
	 * @param iconifiable
	 */
	public DInternalFrame(String title, boolean resizable, boolean closeable,
			boolean maximizable, boolean iconifiable) {
		super(title, resizable, closeable, maximizable, iconifiable);
	}

}
