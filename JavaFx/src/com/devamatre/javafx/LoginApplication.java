/**
 * 
 */
package com.devamatre.javafx;

import javafx.application.Application;
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
		stage.setScene(new Scene(null));
		JFXHelper.setDefaultSize(stage);
		stage.show();
	}
	
}
