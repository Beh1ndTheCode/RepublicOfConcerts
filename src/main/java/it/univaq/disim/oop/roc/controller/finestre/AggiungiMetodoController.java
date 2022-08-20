package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiMetodoController implements DataInitializable<Utente> {

	@FXML
	private TextField nomeCartaField, intestatarioField, numeroField, scadenzaField, meseScadenzaField,
			annoScadenzaField, cvvField, nomeContoField, ibanField, swiftField;

	@FXML
	private Label ibanErrorLabel, numCartaErrorLabel;

	@FXML
	private Button aggiungiCartaButton, aggiungiContoButton;

	private MetodiService metodiService;

	private ViewDispatcher dispatcher;

	private Utente utente;

	public AggiungiMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
	}

	public void initialize() {
		aggiungiCartaButton.setDisable(true);
		aggiungiContoButton.setDisable(true);
	}

	public void initializeData(Utente utente) {
		this.utente = utente;
		System.out.println(utente.getNome());	}

	public void blockAggiungiCartaButton() {
		String nomeCarta = nomeCartaField.getText();
		String intestatario = intestatarioField.getText();
		String numero = numeroField.getText();
		String meseScadenza = meseScadenzaField.getText();
		String annoScadenza = annoScadenzaField.getText();
		String cvv = cvvField.getText();

		boolean isDisable = nomeCarta.isEmpty() || intestatario.isEmpty() || numero.isEmpty() || meseScadenza.isEmpty()
				|| annoScadenza.isEmpty() || cvv.isEmpty();
		aggiungiCartaButton.setDisable(isDisable);
	}

	public void blockAggiungiContoButton() {
		String nomeConto = nomeContoField.getText();
		String iban = ibanField.getText();
		boolean isDisable = nomeConto.isEmpty() || iban.isEmpty();
		aggiungiContoButton.setDisable(isDisable);
	}

	public void aggiungiCartaAction(ActionEvent event) {
		try {
			numCartaErrorLabel.setText("");
			metodiService.addCarta(nomeCartaField.getText(), intestatarioField.getText(), numeroField.getText(),
					meseScadenzaField.getText(), annoScadenzaField.getText(), cvvField.getText());

			nomeCartaField.setText("");
			intestatarioField.setText("");
			numeroField.setText("");
			meseScadenzaField.setText("");
			annoScadenzaField.setText("");
			cvvField.setText("");

		} catch (IntegerFormatException e) {
			numCartaErrorLabel.setText("Inserisci un numero valido");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void aggiungiContoAction(ActionEvent event) {
		try {
			ibanErrorLabel.setText("");
			metodiService.addConto(nomeContoField.getText(), ibanField.getText());
		} catch (IntegerFormatException e) {
			ibanErrorLabel.setText("Iban non valido");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}