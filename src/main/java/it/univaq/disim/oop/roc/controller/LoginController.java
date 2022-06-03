package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginController{
	
	private ViewDispatcher dispatcher;
	
	public LoginController() {
		dispatcher = ViewDispatcher.getInstance();
	}
	
	@FXML
	private Label loginErrorLabel;
	
	@FXML
	private ImageView logo, imageView;
	
	@FXML
	private Text usernameText, passwordText;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	public void initialize() {
		loginButton.setDisable(true);
	}
	
	public void blockLoginButton() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		boolean isDisable = username.isEmpty() || password.isEmpty();
		loginButton.setDisable(isDisable);
	}
	
	public void loginAction(ActionEvent event) {
		if(!("docente".equals(usernameField.getText()) && ("docente".equals(passwordField.getText())))) {
			loginErrorLabel.setText("Username e/o password errati!");
			} 
		else {
			dispatcher.loggedIn();
		}
	}
}
	