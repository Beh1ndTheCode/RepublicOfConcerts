package it.univaq.disim.oop.roc.controller;

import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProfiloController implements DataInitializable<Utente> {

	@FXML
	private Text nameText, surnameText, ageText, usernameText, oldPswText, newPswText, repeatPswText, datiText,
			metodiText;

	@FXML
	private TextField nameTextField, surnameTextField, ageTextField, usernameTextField, oldPswTextField,
			newPswTextField, repeatPswTextField;

	@FXML
	private Button modificaDatiButton, modificaMetodiButton;

	@FXML
	private TableView<MetodoDiPagamento> metodiTableView;

	@FXML
	private TableColumn<MetodoDiPagamento, String> tipoTableColumn, nomeTableColumn;

	@FXML
	private TableColumn<MetodoDiPagamento, Button> azioniTableColumn;

	private ViewDispatcher dispatcher;

	private MetodiService metodiService;

	private Utente utente;

	public ProfiloController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
	}

	@Override
	public void initializeData(Utente utente) {
		usernameTextField.setPromptText(utente.getUsername());
		nameTextField.setPromptText(utente.getNome());
		surnameTextField.setPromptText(utente.getCognome());
		try {
			List<MetodoDiPagamento> metodi = metodiService.findAllMetodi(utente);
			ObservableList<MetodoDiPagamento> metodiData = FXCollections.observableArrayList(metodi);
			metodiTableView.setItems(metodiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void addMetodoAction(ActionEvent event) throws Exception {
		dispatcher.renderView("aggiungiMetodi", utente);
	}

}