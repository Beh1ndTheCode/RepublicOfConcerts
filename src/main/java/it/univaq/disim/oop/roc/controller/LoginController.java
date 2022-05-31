package it.univaq.disim.oop.roc.controller;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginController{
	
	@FXML
	ImageView logo, imageView;
	
	Text usarnameText, passwordText;
	
	TextField usernameField;
	
	PasswordField passwordField;
	
	Button loginButton;
	
	public void initialize(URL url, ResourceBundle rb) {
		loginButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
	}
	
	public void loginAction(ActionEvent event) {
		/* if(!("docente".equals(username.getText()) && ("docente".equals(password.getText())))) {
			loginErrorLabel.setText("Username e/o password errati!");
			} 
		else {
				Bisogna caricare la vista successiva
		}
		*/
	}
}
	
