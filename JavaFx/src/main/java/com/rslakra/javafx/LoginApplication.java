/**
 * 
 */
package com.rslakra.javafx;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-05 08:46:26 PM
 * @version 1.0.0
 * @since 1.0.0
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
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			URL url = getClass().getResource("loginScene.fxml");
			FXMLLoader xmlLoader = new FXMLLoader(getClass().getResource("loginScene.fxml"));
			Parent root = xmlLoader.load();
			stage.setScene(new Scene(root));
			JFXHelper.setDefaultSize(stage);
			stage.show();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
