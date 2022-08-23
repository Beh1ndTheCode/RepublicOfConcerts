package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.TipeFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiLuogoController implements DataInitializable<Object>{
	
	@FXML
	private Label capienzaErrorLabel, tipoErrorLabel;
	
	@FXML
	private TextField tipoTextField, nomeTextField, cittaTextField, capienzaTextField;
	
	@FXML
	private Button aggiungiButton;
	
	private ViewDispatcher dispatcher;
	
	private LuogoService luoghiService;
	
	public AggiungiLuogoController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}
	
	public void initialize() {
		aggiungiButton.setDisable(true);
	}
	
	public void blockAggiungiButton() {
		String tipo = tipoTextField.getText();
		String nome = nomeTextField.getText();
		String citta = cittaTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() || citta.isEmpty() || capienza.isEmpty() || tipo.isEmpty();
		aggiungiButton.setDisable(isDisable);
	}
	
	public void addLuogoAction(ActionEvent event) {
		try {
			Integer capienza;
			try {
				capienza = Integer.parseInt(capienzaTextField.getText());
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			capienzaErrorLabel.setText("");
			luoghiService.addLuogo(tipoTextField.getText(), nomeTextField.getText(), cittaTextField.getText(), capienza);

			nomeTextField.setText("");
			tipoTextField.setText("");
			cittaTextField.setText("");
			capienzaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestioneluoghi");
		} catch (TipeFormatException e) {
			tipoErrorLabel.setText("tipo non valido, teatro o stadio");
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
