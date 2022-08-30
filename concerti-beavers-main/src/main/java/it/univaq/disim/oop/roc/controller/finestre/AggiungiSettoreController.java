package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiSettoreController implements DataInitializable<Luogo> {

	@FXML
	private Label capienzaErrorLabel;

	@FXML
	private TextField nomeTextField, capienzaTextField;

	@FXML
	private Button aggiungiSettoreButton;

	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;

	private Luogo luogo;

	public AggiungiSettoreController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		aggiungiSettoreButton.setDisable(true);
	}

	public void initializeData(Luogo luogo) {
		this.luogo = luogo;
	}

	public void blockAggiungiButton() {
		String nome = nomeTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() || capienza.isEmpty();
		aggiungiSettoreButton.setDisable(isDisable);
	}

	public void addSettoreAction(ActionEvent event) {
		try {
			capienzaErrorLabel.setText("");
			luoghiService.addSettore(luogo, nomeTextField.getText(), capienzaTextField.getText());
			nomeTextField.setText("");
			capienzaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestionesettori", luogo);
		} catch (NumberOutOfBoundsException e) {
			capienzaErrorLabel.setText("ridurre la capienza");
		} catch (IntegerFormatException e) {
			capienzaErrorLabel.setText("capienza non valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}

}
