package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AggiungiMetodoController implements DataInitializable<Object>{

	@FXML
	private TextField nomeCartaField, intestatarioField, numeroField, scadenzaField, cvvField, nomeContoField, ibanField, swiftField;
	
	@FXML
	private Button aggiungiCartaButton, aggiungiContoButton;
	
	public AggiungiMetodoController() {
	}

}