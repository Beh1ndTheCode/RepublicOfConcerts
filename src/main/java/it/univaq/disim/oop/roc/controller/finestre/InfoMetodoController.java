package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoMetodoController implements DataInitializable<Object> {

	@FXML
	private Text numeroCartaText, intestatarioText, nomeCartaText, scadenzaText, nomeContoText, ibanText, swiftText;

	@FXML
	private Label numeroCartaLabel, intestatarioLabel, nomeCartaLabel, scadenzaLabel, nomeContoLabel, ibanLabel,
			swiftLabel;

	private ViewDispatcher dispatcher;

	private MetodoDiPagamento metodo;

	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initialize() {

	}

	public void initializeData(MetodoDiPagamento metodo) {
		this.metodo = metodo;
		if (metodo instanceof Carta) {
			nomeCartaText.setText("Nome");
			numeroCartaText.setVisible(true);
			intestatarioText.setVisible(true);
			scadenzaText.setVisible(true);
		}
		if (metodo instanceof Conto) {
			nomeContoText.setText("Nome");
			ibanText.setVisible(true);
			swiftText.setVisible(true);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
