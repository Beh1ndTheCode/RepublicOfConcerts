package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.domain.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController implements DataInitializable<Utente> {

	@FXML
	private Label benvenutoLabel;
	
	 public void initializeData(Utente utente) { 
	 StringBuilder testo = new StringBuilder(); 
	 testo.append("Benvenuto "); 
	 testo.append(utente.getNome());
	 testo.append(" "); 
	 testo.append(utente.getCognome());
	 benvenutoLabel.setText(testo.toString()); 
	 }
}
