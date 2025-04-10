package it.univaq.disim.oop.roc.controller.viste.spettatore;

import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
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
	private TableColumn<MetodoDiPagamento, Button> azioniTableColumn, preferitoTableColumn;

	private ViewDispatcher dispatcher;

	private MetodiService metodiService;

	private Spettatore spettatore;

	private UtenteService utenteService;

	public ProfiloController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		utenteService = factory.getUtenteService();
		metodiService = factory.getMetodiService();
	}

	//creazione di tutta la tabella dei Metodi di pagamento
	public void initialize() {
		modificaDatiButton.setDisable(true);
		tipoTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, String> param) -> {
			if (param.getValue() instanceof Carta)
				return new SimpleStringProperty("Carta");
			else
				return new SimpleStringProperty("Conto");
		});
		nomeTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
		azioniTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, Button> param) -> {
			final Button infoButton = new Button("Info");
			infoButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("infometodo", param.getValue());
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});
		preferitoTableColumn.setCellValueFactory((CellDataFeatures<MetodoDiPagamento, Button> param) -> {
			final Button preferitoButton = new Button("Seleziona");
			if (spettatore.getMetodoPreferito() == null
					|| !param.getValue().getId().equals(spettatore.getMetodoPreferito().getId())) {
				preferitoButton.setOnAction(e -> {
					try {
						spettatore.setMetodoPreferito(param.getValue());
						utenteService.updateDati(spettatore);
						dispatcher.renderView("profilo", spettatore);
					} catch (BusinessException ex) {
						dispatcher.renderError(ex);
					}
				});
			} else {
				preferitoButton.setText("Preferito");
				preferitoButton.setDisable(true);
			}
			return new SimpleObjectProperty<Button>(preferitoButton);
		});

	}

	@Override	//inizializzazione delle Label e ricerca dei Metodi di pagamento tramite Utente
	public void initializeData(Utente utente) {
		this.spettatore = (Spettatore) utente;
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
		String oldPassword = oldPswField.getText();
		String newPassword = newPswField.getText();
		String name = nameField.getText();
		String surname = surnameField.getText();
		String username = usernameField.getText();
		String età = ageField.getText();
		boolean isDisable = oldPassword.isEmpty() || (newPassword.isEmpty() && name.isEmpty() && surname.isEmpty()
				&& username.isEmpty() && età.isEmpty());
		modificaDatiButton.setDisable(isDisable);
	}
	
	//esegue l'update dei dati quando viene cliccato il bottone Modifica
	//vengono fatte le verifiche e se necessario blocca l'update e attiva le ErrorLabel
	public void updateDatiAction(ActionEvent event) {
		ageErrorLabel.setText("");
		oldPswErrorLabel.setText("");
		repeatPswErrorLabel.setText("");
		try {
			if (spettatore.getPassword().equals(oldPswField.getText())) {
				if (newPswField.getText().equals(repeatPswField.getText())) {

					if (!ageField.getText().equals("")) {
						Integer ageInput;
						try {
							ageInput = Integer.parseInt(ageField.getText());
							if (ageInput > 0 && ageInput < 100)
								spettatore.setEta(ageInput);
						} catch (NumberFormatException n) {
							ageErrorLabel.setText("Età non valida!");
						}
					}

					if (!usernameField.getText().isEmpty())
						spettatore.setUsername(usernameField.getText());
					if (!nameField.getText().isEmpty())
						spettatore.setNome(nameField.getText());
					if (!surnameField.getText().isEmpty())
						spettatore.setCognome(surnameField.getText());
					if (!newPswField.getText().isEmpty())
						spettatore.setPassword(newPswField.getText());
					utenteService.updateDati(spettatore);

					dispatcher.renderView("profilo", spettatore);
				}
				throw new InvalidPasswordException();
			}
			throw new UtenteNotFoundException();

		} catch (UtenteNotFoundException e) {
			oldPswErrorLabel.setText("Password errata!");
		} catch (InvalidPasswordException e) {
			repeatPswErrorLabel.setText("Le password non coincidono!");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void openAggiungiMetodoWindow(ActionEvent event) throws Exception {
		dispatcher.openNewWindow("aggiungimetodo", spettatore);
	}

}