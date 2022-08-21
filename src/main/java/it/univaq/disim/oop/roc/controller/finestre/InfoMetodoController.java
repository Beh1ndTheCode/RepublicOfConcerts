package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoMetodoController implements DataInitializable<Object> {
	
	@FXML
	private Text numeroCartaText, intestatarioText, nomeCartaText, scadenzaText, nomeContoText, ibanText, swiftText;
	
	@FXML
	private Label numeroCartaLabel, intestatarioLabel, nomeCartaLabel, scadenzaLabel, nomeContoLabel, ibanLabel, swiftLabel;
	
	private ViewDispatcher dispatcher;
	
	private Utente utente;
	
	private MetodoDiPagamento metodo;
	
	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
	}
	
	public void inizialize() {
		
	}
	
	public void inizializeData(Utente utente) {
		this.utente = utente;
	}
	
	public void inizializeData(MetodoDiPagamento metodo) {
		this.metodo = metodo;
		if(metodo.getTipo().equals("Carta")) {
			numeroCartaText.setVisible(true);
			intestatarioText.setVisible(true);
			nomeCartaText.setVisible(true);
			scadenzaText.setVisible(true);
		}
		if(metodo.getTipo().equals("Conto")) {
			nomeContoText.setVisible(true);
			ibanText.setVisible(true);
			swiftText.setVisible(true);
		}
	}
	
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
