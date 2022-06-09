
package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignupController {

	@FXML
	private Text usernameText, passwordText, ripetiPasswordText, nameText, surnameText, ageText;

	@FXML
	private TextField usernameField, nameField, ageField, surnameField;

	@FXML
	private PasswordField passwordField, ripetiPasswordField;

	@FXML
	private Button signupButton;

	private UtenteService utenteService;

	private ViewDispatcher dispatcher;

	public void initialize() {
		signupButton.setDisable(true);
	}

	public void blockSignupButton() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		String name = nameField.getText();
		String surname = surnameField.getText();
		String age = ageField.getText();
		boolean isDisable = username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()
				|| age.isEmpty();
		signupButton.setDisable(isDisable);
	}

	public void signupAction(ActionEvent event) {
		Utente utente = utenteService.registration(usernameField.getText(), passwordField.getText(),
				nameField.getText(), surnameField.getText(), Integer.parseInt(ageField.getText()));
		// crea un oggetto utente invocando il metodo registration di utenteService
		dispatcher.signedUp(utente);
	}

}
