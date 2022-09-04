package it.univaq.disim.oop.roc.controller.finestre;

import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.impl.file.FileMetodiServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoMetodoController implements DataInitializable<MetodoDiPagamento> {

	@FXML
	private Text numeroCartaText, intestatarioText, nomeCartaText, scadenzaText, nomeContoText, ibanText, swiftText;

	@FXML
	private Label numeroCartaLabel, intestatarioLabel, nomeCartaLabel, scadenzaLabel, nomeContoLabel, ibanLabel,
			swiftLabel;
	@FXML
	private Button eliminaButton;

	private ViewDispatcher dispatcher;

	private MetodiService metodiService;

	private Carta carta;

	private Conto conto;

	private MetodoDiPagamento metodo;

	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
		// metodiService = new FileMetodiServiceImpl();
	}

	public void initialize() {
	}

	public void initializeData(MetodoDiPagamento metodo) {
		this.metodo = metodo;
		if (metodo instanceof Carta) {
			carta = (Carta) metodo;
			nomeCartaText.setVisible(true);
			numeroCartaText.setVisible(true);
			intestatarioText.setVisible(true);
			scadenzaText.setVisible(true);
			numeroCartaLabel.setText(Long.toString(carta.getNumero()));
			intestatarioLabel.setText(carta.getIntestatario());
			nomeCartaLabel.setText(carta.getNome());
			scadenzaLabel.setText(carta.getScadenza().format(DateTimeFormatter.ofPattern("MM/yy")));
		}

		if (metodo instanceof Conto) {
			conto = (Conto) metodo;
			nomeContoText.setVisible(true);
			ibanText.setVisible(true);
			swiftText.setVisible(true);
			nomeContoLabel.setText(conto.getNome());
			ibanLabel.setText(conto.getIban());
			swiftLabel.setText(conto.getSwift());
		}
	}

	public void deleteAction(ActionEvent event) {
		try {
			metodiService.deleteMetodo(metodo);
			dispatcher.closeWindowView();
			dispatcher.renderView("profilo", metodo.getUtente());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}

	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}