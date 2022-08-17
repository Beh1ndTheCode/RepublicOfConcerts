package it.univaq.disim.oop.roc.controller;

import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMUtenteServiceImpl;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProfiloController implements DataInitializable<Utente> {

	@FXML
	private Text nameText, surnameText, ageText, usernameText, oldPswText, newPswText, repeatPswText, datiText,
			metodiText;

	@FXML
	private TextField nameField, surnameField, ageField, usernameField;

	@FXML
	private PasswordField oldPswField, newPswField, repeatPswField;

	@FXML
	private Label oldPswErrorLabel, repeatPswErrorLabel, ageErrorLabel;

	@FXML
	private Button modificaDatiButton, aggiungiMetodiButton;

	@FXML
	private TableView<MetodoDiPagamento> metodiTableView;

	@FXML
	private TableColumn<MetodoDiPagamento, String> tipoTableColumn, nomeTableColumn;

	@FXML
	private TableColumn<MetodoDiPagamento, Button> azioniTableColumn;

	private ViewDispatcher dispatcher;

	private MetodiService metodiService;

	private Utente utente;

	private UtenteService utenteService;

	public ProfiloController() {
		dispatcher = ViewDispatcher.getInstance();
		utenteService = new RAMUtenteServiceImpl();
		metodiService = new RAMMetodiServiceImpl();
	}

	public void initialize() {
		modificaDatiButton.setDisable(true);
	}

	@Override
	public void initializeData(Utente utente) {
		this.utente = utente;
		usernameField.setPromptText(utente.getUsername());
		nameField.setPromptText(utente.getNome());
		surnameField.setPromptText(utente.getCognome());
		ageField.setPromptText(String.valueOf(utente.getEta()));
		try {
			List<MetodoDiPagamento> metodi = metodiService.findAllMetodi(utente);
			ObservableList<MetodoDiPagamento> metodiData = FXCollections.observableArrayList(metodi);
			metodiTableView.setItems(metodiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void blockModificaDatiButton() {
		String password = oldPswField.getText();
		boolean isDisable = password.isEmpty();
		modificaDatiButton.setDisable(isDisable);
	}

	// Revisionare
	public void updateDatiAction(ActionEvent event) {
		try {
			ageErrorLabel.setText("");
			oldPswErrorLabel.setText("");
			repeatPswErrorLabel.setText("");
			if (!ageField.getText().equals("\\d*")) {
				String ageInput;
				ageInput = (ageField.getText().replaceAll("[^\\d]", ""));
				if (!(ageInput == null || ageInput.length() == 0)) {
					utenteService.updateDati(utente, nameField.getText(), surnameField.getText(),
							usernameField.getText(), Integer.parseInt(ageInput), oldPswField.getText(),
							newPswField.getText(), repeatPswField.getText());
				}
			} else {
				utenteService.updateDati(utente, nameField.getText(), surnameField.getText(), usernameField.getText(),
						utente.getEta(), oldPswField.getText(), newPswField.getText(), repeatPswField.getText());
			}
			initializeData(utente);
			// azzero tutti i campi così da mostrare i dati modificati nel promptText
			nameField.setText("");
			surnameField.setText("");
			usernameField.setText("");
			ageField.setText("");
			oldPswField.setText("");
			newPswField.setText("");
			repeatPswField.setText("");
		} catch (EtaFormatException e) {
			ageErrorLabel.setText("Età non valida!");
		} catch (UtenteNotFoundException e) {
			oldPswErrorLabel.setText("Password errata!");
		} catch (InvalidPasswordException e) {
			repeatPswErrorLabel.setText("Le password non coincidono!");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void addMetodoAction(ActionEvent event) throws Exception {
		dispatcher.renderView("aggiungiMetodi", utente);
	}

}