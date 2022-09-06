package it.univaq.disim.oop.roc.controller.viste;

import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
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

	private Utente utente;

	public SignupController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
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

	@FXML
	public void signupAction(ActionEvent event) {
		passwordErrorLabel.setText("");
		etaErrorLabel.setText("");
		Integer ageInput;
		try {
			ageInput = Integer.parseInt(ageField.getText());
			if (ageInput > 0 && ageInput < 100) {
				if (passwordField.getText().equals(ripetiPasswordField.getText())) {
					Spettatore spettatore = new Spettatore();
					spettatore.setUsername(usernameField.getText());
					spettatore.setPassword(passwordField.getText());
					spettatore.setNome(nameField.getText());
					spettatore.setCognome(surnameField.getText());
					spettatore.setEta(ageInput);
					utente = utenteService.registration(spettatore);
					dispatcher.signedUp(utente);
				}
				throw new InvalidPasswordException();
			}
			throw new NumberFormatException();

		} catch (InvalidPasswordException e) {
			passwordErrorLabel.setText("Le password non coincidono!");
		} catch (NumberFormatException e) {
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
