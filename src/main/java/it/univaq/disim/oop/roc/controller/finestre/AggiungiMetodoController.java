package it.univaq.disim.oop.roc.controller.finestre;

import java.time.LocalDate;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.Utility;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiMetodoController implements DataInitializable<Utente> {

	@FXML
	private TextField nomeCartaField, intestatarioField, numeroField1, numeroField2, numeroField3, numeroField4,
			meseScadenzaField, annoScadenzaField, cvvField, nomeContoField, ibanField, swiftField;

	@FXML
	private Label ibanErrorLabel, numErrorLabel, dataErrorLabel;

	@FXML
	private Button aggiungiCartaButton, aggiungiContoButton;

	private MetodiService metodiService;

	private ViewDispatcher dispatcher;

	private Utente utente;

	public AggiungiMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
		// metodiService = new FileMetodiServiceImpl();
	}

	public void initialize() {
		aggiungiCartaButton.setDisable(true);
		aggiungiContoButton.setDisable(true);
	}

	public void initializeData(Utente utente) {
		this.utente = utente;
	}

	public void blockAggiungiCartaButton() {
		String nomeCarta = nomeCartaField.getText();
		String intestatario = intestatarioField.getText();
		String numero = numeroField1.getText() + numeroField2.getText() + numeroField3.getText()
				+ numeroField4.getText();
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
		numErrorLabel.setText("");
		dataErrorLabel.setText("");

		try {
			if (numeroField1.getText().length() == 4 && numeroField2.getText().length() == 4
					&& numeroField3.getText().length() == 4 && numeroField4.getText().length() == 4) {

				String numero = numeroField1.getText() + numeroField2.getText() + numeroField3.getText()
						+ numeroField4.getText();
				Long numeroInput;
				numeroInput = Long.parseLong(numero);

				if (cvvField.getText().length() == 3) {
					Integer cvvInput;
					cvvInput = Integer.parseInt(cvvField.getText());

					LocalDate data = Utility.VerificaData("01", meseScadenzaField.getText(),
							annoScadenzaField.getText());

					Carta carta = new Carta();
					carta.setNome(nomeCartaField.getText());
					carta.setUtente(utente);
					carta.setIntestatario(intestatarioField.getText());
					carta.setNumero(numeroInput);
					carta.setScadenza(data);
					carta.setCvv(cvvInput);
					metodiService.addMetodo(carta);

					dispatcher.renderView("profilo", utente);
					nomeCartaField.setText("");
					intestatarioField.setText("");
					numeroField1.setText("");
					numeroField2.setText("");
					numeroField3.setText("");
					numeroField4.setText("");
					meseScadenzaField.setText("");
					annoScadenzaField.setText("");
					cvvField.setText("");
					blockAggiungiCartaButton();

					return;
				}
				throw new IntegerFormatException();
			}
			throw new NumberFormatException();

		} catch (NumberFormatException e) {
			numErrorLabel.setText("Inserisci un numero carta valido");
		} catch (IntegerFormatException e) {
			numErrorLabel.setText("Inserisci un CVV valido");
		} catch (InvalidDateException e) {
			dataErrorLabel.setText("Inserisci una scadenza valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void aggiungiContoAction(ActionEvent event) {
		ibanErrorLabel.setText("");

		try {
			if (ibanField.getText().length() == 27) {
				Conto conto = new Conto();
				conto.setNome(nomeContoField.getText());
				conto.setIban(ibanField.getText());
				conto.setSwift(swiftField.getText());
				conto.setUtente(utente);
				metodiService.addMetodo(conto);

				dispatcher.renderView("profilo", utente);
				nomeContoField.setText("");
				ibanField.setText("");
				swiftField.setText("");
				blockAggiungiContoButton();

				return;
			}
			throw new IntegerFormatException();

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