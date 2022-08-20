package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoCartaMetodoController implements DataInitializable<Utente> {
	
	@FXML
	private Label numeroCartaLabel, intestatarioLabel, nomeCartaLabel, scadenzaLabel;
	
	private ViewDispatcher dispatcher;
	
	private Utente utente;
	
	public InfoCartaMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
	}
	
	public void inizialize() {
		
	}
	
	public void inizializeData() {
		
	}
	
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
