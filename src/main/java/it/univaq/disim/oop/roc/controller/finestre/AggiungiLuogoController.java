package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiLuogoController implements DataInitializable<Luogo> {

	@FXML
	private Label capienzaErrorLabel;

	@FXML
	private TextField nomeTextField, cittaTextField, capienzaTextField;

	@FXML
	private Button aggiungiTeatroButton, aggiungiStadioButton;

	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;

	public AggiungiLuogoController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		aggiungiTeatroButton.setDisable(true);
		aggiungiStadioButton.setDisable(true);
	}

	public void blockAggiungiButton() {
		String nome = nomeTextField.getText();
		String citta = cittaTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() || citta.isEmpty() || capienza.isEmpty();
		aggiungiTeatroButton.setDisable(isDisable);
		aggiungiStadioButton.setDisable(isDisable);
	}

	public void addTeatroAction(ActionEvent event) {
		try {
			Luogo teatro = new Teatro();
			capienzaErrorLabel.setText("");
			luoghiService.addLuogo(teatro, nomeTextField.getText(), cittaTextField.getText(),
					capienzaTextField.getText());

			nomeTextField.setText("");
			cittaTextField.setText("");
			capienzaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestioneluoghi");
		} catch (

		IntegerFormatException e) {
			capienzaErrorLabel.setText("capienza non valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void addStadioAction(ActionEvent event) {
		try {
			Luogo stadio = new Stadio();
			capienzaErrorLabel.setText("");
			luoghiService.addLuogo(stadio, nomeTextField.getText(), cittaTextField.getText(),
					capienzaTextField.getText());

			nomeTextField.setText("");
			cittaTextField.setText("");
			capienzaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestioneluoghi");
		} catch (

		IntegerFormatException e) {
			capienzaErrorLabel.setText("capienza non valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
