/**
 * 
 */
package com.devamatre.javafx;

import javafx.stage.Stage;

/**
 * @author Rohtash Singh Lakra (Rohtash.Lakra@nasdaq.com)
 * @date 02/16/2018 01:00:40 PM
 */
public final class JavaFXHelper {
	
	private JavaFXHelper() {
		
	}
	
	/**
	 * Sets the width and height of the stage.
	 * 
	 * @param stage
	 * @param width
	 * @param height
	 */
	public static void setSize(final Stage stage, final double width, final double height) {
		stage.setWidth(640);
		stage.setHeight(480);
	}
	
}
