/**
 * 
 */
package com.rslakra.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Rohtash Singh Lakra
 * @date 03/05/2018 04:25:51 PM
 */
public class LoginController implements Initializable {
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField password;
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 *      java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void okAction(ActionEvent actionEvent) {
		System.out.println("OK Button Clicked!");
		if (userName != null) {
			System.out.println("User Name:" + userName.getText());
		}
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void cancelAction(ActionEvent actionEvent) {
		System.out.println("Cancel Button Clicked!");
		if (userName != null) {
			userName.setText("");
		}
		
		if (userName != null) {
			password.setText("");
		}
	}
	
}
