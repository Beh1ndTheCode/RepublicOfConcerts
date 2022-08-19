package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AggiungiMetodoController implements DataInitializable<Object> {

	@FXML
	private TextField nomeCartaField, intestatarioField, numeroField, scadenzaField, cvvField, nomeContoField,
			ibanField, swiftField;

	@FXML
	private Button aggiungiCartaButton, aggiungiContoButton;

	private MetodiService metodiService;

	private ViewDispatcher dispatcher;

	private Carta carta;

	private Conto conto;

	public AggiungiMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
	}

	public void blockAggiungiCartaButton() {
		String nomeCarta = nomeCartaField.getText();
		String intestatario = intestatarioField.getText();
		String numero = numeroField.getText();
		String scadenza = scadenzaField.getText();
		String cvv = cvvField.getText();
		boolean isDisable = nomeCarta.isEmpty() || intestatario.isEmpty() || numero.isEmpty() || scadenza.isEmpty()
				|| cvv.isEmpty();
		aggiungiCartaButton.setDisable(isDisable);
	}

	public void blockAggiungiContoButton() {
		String nomeConto = nomeContoField.getText();
		String iban = ibanField.getText();
		boolean isDisable = nomeConto.isEmpty() || iban.isEmpty();
		aggiungiCartaButton.setDisable(isDisable);
	}

	public void aggiungiCartaAction(ActionEvent event) {
		try {
			Integer cvvInput;
			try {
				cvvInput = Integer.parseInt(cvvField.getText());
			} catch (NumberFormatException n) {
				throw new EtaFormatException();
			}
			carta = new Carta();
			metodiService.addCarta(carta, nomeCartaField.getText(), intestatarioField.getText(), numeroField.getText(),
					scadenzaField.getText(), cvvInput);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void aggiungiContoAction(ActionEvent event) {
		try {
			conto = new Conto();
			metodiService.addConto(conto, nomeContoField.getText(), ibanField.getText());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}