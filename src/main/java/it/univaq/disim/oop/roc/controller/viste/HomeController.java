package it.univaq.disim.oop.roc.controller.viste;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController implements UtenteInitializable<Utente>, DataInitializable<Boolean> {

	@FXML
	private Label benvenutoLabel, registrazioneLabel;
	
	 public void initializeUtente(Utente utente) { 
		 StringBuilder testo = new StringBuilder(); 
		 testo.append("Benvenuto "); 
		 testo.append(utente.getNome());
		 testo.append(" "); 
		 testo.append(utente.getCognome());
		 benvenutoLabel.setText(testo.toString()); 
	 }
	 
	 public void initializeBool(boolean registrazione) {
		 if (registrazione) {
			 registrazioneLabel.setText("Registrazione avvenuta con successo");
		 }
	}
}
