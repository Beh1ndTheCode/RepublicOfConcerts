package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMUtenteServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
		tipoTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, String> param) -> {
			return new SimpleStringProperty(param.getValue().getTipo());
		});
		nomeTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
		azioniTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, Button> param) -> {
			final Button metodiButton = new Button("Di più");
			metodiButton.setOnAction((ActionEvent event) -> {
				oldPswErrorLabel.setText("Successo");
			});
			return new SimpleObjectProperty<Button>(metodiButton);
		});
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
		String oldpassword = oldPswField.getText();
		String newpassword = newPswField.getText();
		String name = nameField.getText();
		String surname = surnameField.getText();
		String username = usernameField.getText();
		String età = ageField.getText();
		boolean isDisable = oldpassword.isEmpty() || (newpassword.isEmpty() && name.isEmpty() && surname.isEmpty()
				&& username.isEmpty() && età.isEmpty());
		modificaDatiButton.setDisable(isDisable);
	}

	public void updateDatiAction(ActionEvent event) {
		try {
			ageErrorLabel.setText("");
			oldPswErrorLabel.setText("");
			repeatPswErrorLabel.setText("");
			Integer ageInput;
			if (!ageField.getText().equals("")) {
				try {
					ageInput = Integer.parseInt(ageField.getText());
				} catch (NumberFormatException n) {
					throw new IntegerFormatException();
				}
			} else
				ageInput = utente.getEta();
			utenteService.updateDati(utente, nameField.getText(), surnameField.getText(), usernameField.getText(),
					ageInput, oldPswField.getText(), newPswField.getText(), repeatPswField.getText());
			initializeData(utente);
			// azzero tutti i campi così da mostrare i dati modificati nel promptText
			nameField.setText("");
			surnameField.setText("");
			usernameField.setText("");
			ageField.setText("");
			oldPswField.setText("");
			newPswField.setText("");
			repeatPswField.setText("");
		} catch (IntegerFormatException e) {
			ageErrorLabel.setText("Età non valida!");
		} catch (UtenteNotFoundException e) {
			oldPswErrorLabel.setText("Password errata!");
		} catch (InvalidPasswordException e) {
			repeatPswErrorLabel.setText("Le password non coincidono!");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void openAggiungiMetodoWindow(ActionEvent event) throws Exception {
		dispatcher.windowView("aggiungimetodo");
	}

}