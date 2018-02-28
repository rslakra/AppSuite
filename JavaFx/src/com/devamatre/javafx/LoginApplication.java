/**
 * 
 */
package com.devamatre.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Rohtash Singh Lakra
 */
public class LoginApplication extends Application {
	
	/**
	 * 
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
		Label userNameLabel = new Label("Username:");
		TextField userName = new TextField();
		HBox boxUserName = new HBox();
		boxUserName.setPadding(new Insets(10, 10, 10, 10));
		stage.setScene(new Scene(boxUserName));
		JavaFXHelper.setSize(stage, 640, 480);
		stage.show();
	}
	
}
