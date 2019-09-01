/**
 * 
 */
package com.rslakra.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Rohtash Singh Lakra
 */
public class HelloWorld extends Application {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		String title = "Hello World!";
		Label message = new Label(title);
		message.setFont(new Font(50));
		
		stage.setScene(new Scene(message));
		stage.setTitle(title);
		JFXHelper.setDefaultSize(stage);
		stage.show();
		
	}
	
}
