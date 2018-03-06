/**
 * 
 */
package com.devamatre.javafx;

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
