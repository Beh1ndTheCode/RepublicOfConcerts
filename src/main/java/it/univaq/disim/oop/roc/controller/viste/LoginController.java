package it.univaq.disim.oop.roc.controller.viste;

import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginController implements DataInitializable<Object> {

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
	private Button loginButton, signupViewButton;

	private UtenteService utenteService;

	private ViewDispatcher dispatcher;

	public LoginController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
	}

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
		try {
			Utente utente = utenteService.authenticate(usernameField.getText(), passwordField.getText());
			dispatcher.loggedIn(utente);
		} catch (UtenteNotFoundException e) {
			loginErrorLabel.setText("Username e/o password errati!");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void goToSignupView(ActionEvent event) throws Exception {
		dispatcher.signupView();
	}
}
