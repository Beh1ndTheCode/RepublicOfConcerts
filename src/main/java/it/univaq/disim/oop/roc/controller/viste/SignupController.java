package it.univaq.disim.oop.roc.controller.viste;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMUtenteServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignupController implements DataInitializable<Utente> {

	@FXML
	private Label etaErrorLabel, passwordErrorLabel;

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

	public SignupController() {
		dispatcher = ViewDispatcher.getInstance();
		utenteService = new RAMUtenteServiceImpl();
	}

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

	// Revisionare
	public void signupAction(ActionEvent event) {
		try {
			passwordErrorLabel.setText("");
			etaErrorLabel.setText("");
			if (!ageField.getText().equals("\\d*")) {
				String ageInput;
				ageInput = (ageField.getText().replaceAll("[^\\d]", ""));
				if (!(ageInput == null || ageInput.length() == 0)) {
					Utente utente = utenteService.registration(usernameField.getText(), passwordField.getText(),
							ripetiPasswordField.getText(), nameField.getText(), surnameField.getText(),
							Integer.parseInt(ageInput));
					dispatcher.signedUp(utente);

				}
			} else {
				Utente utente = utenteService.registration(usernameField.getText(), passwordField.getText(),
						ripetiPasswordField.getText(), nameField.getText(), surnameField.getText(), 28);
				dispatcher.signedUp(utente);

			}
		} catch (InvalidPasswordException e) {
			passwordErrorLabel.setText("Le password non coincidono!");
		} catch (EtaFormatException e) {
			etaErrorLabel.setText("Età non valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void goToLoginView(ActionEvent event) throws Exception {
		try {
			dispatcher.loginView();
		} catch (ViewException e) {
			e.printStackTrace();
		}
	}

}
