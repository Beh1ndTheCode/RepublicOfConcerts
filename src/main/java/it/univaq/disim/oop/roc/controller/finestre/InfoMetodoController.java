package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoMetodoController implements DataInitializable<MetodoDiPagamento> {

	@FXML
	private Text numeroCartaText, intestatarioText, nomeCartaText, scadenzaText, nomeContoText, ibanText, swiftText;

	@FXML
	private Label numeroCartaLabel, intestatarioLabel, nomeCartaLabel, scadenzaLabel, nomeContoLabel, ibanLabel,
			swiftLabel;

	private ViewDispatcher dispatcher;

	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initialize() {
	}

	public void initializeData(MetodoDiPagamento metodoDiPagamento) {
		if (metodoDiPagamento instanceof Carta) {
			Carta carta = (Carta) metodoDiPagamento;
			nomeCartaText.setVisible(true);
			numeroCartaText.setVisible(true);
			intestatarioText.setVisible(true);
			scadenzaText.setVisible(true);
			numeroCartaLabel.setText(Long.toString(carta.getNumero()));
			intestatarioLabel.setText(carta.getIntestatario());
			nomeCartaLabel.setText(carta.getNome());
			scadenzaLabel.setText(
					(Integer.toString(carta.getMeseScadenza())) + " / " + (Integer.toString(carta.getAnnoScadenza())));
		}

		if (metodoDiPagamento instanceof Conto) {
			Conto conto = (Conto) metodoDiPagamento;
			nomeContoText.setVisible(true);
			ibanText.setVisible(true);
			swiftText.setVisible(true);
			nomeContoLabel.setText(conto.getNome());
			ibanLabel.setText(conto.getIban());
			swiftLabel.setText(conto.getSwift());
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
