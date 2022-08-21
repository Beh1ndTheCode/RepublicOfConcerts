package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
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

	private MetodoDiPagamento metodoDiPagamento;
	
	private Carta carta;
	
	private Conto conto;

	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initialize() {
	}

	public void initializeData(MetodoDiPagamento metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
		if (metodoDiPagamento instanceof Carta) {
			nomeCartaText.setVisible(true);
			numeroCartaText.setVisible(true);
			intestatarioText.setVisible(true);
			scadenzaText.setVisible(true);
			numeroCartaLabel.setText(Long.toString(((Carta) metodoDiPagamento).getNumero()));
			intestatarioLabel.setText(((Carta) metodoDiPagamento).getIntestatario());
			nomeCartaLabel.setText(((Carta) metodoDiPagamento).getNome());
			scadenzaLabel.setText((Integer.toString(((Carta) metodoDiPagamento).getMeseScadenza())) + " / " + (Integer.toString(((Carta) metodoDiPagamento).getAnnoScadenza())));
		}
		if (metodoDiPagamento instanceof Conto) {
			nomeContoText.setVisible(true);
			ibanText.setVisible(true);
			swiftText.setVisible(true);
			nomeContoLabel.setText(((Conto) metodoDiPagamento).getNome());
			ibanLabel.setText(((Conto) metodoDiPagamento).getIban());
			swiftLabel.setText(((Conto) metodoDiPagamento).getSwift());
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
