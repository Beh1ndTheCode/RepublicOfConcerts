package it.univaq.disim.oop.roc.controller.finestre.spettatore;

import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
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
	private Text numeroText, intestatarioText, nomeText, scadenzaText;

	@FXML
	private Label numeroLabel, intestatarioLabel, nomeLabel, scadenzaLabel;

	@FXML
	private Button eliminaButton;

	private ViewDispatcher dispatcher;

	private MetodiService metodiService;

	private Carta carta;

	private Conto conto;

	private MetodoDiPagamento metodo;

	public InfoMetodoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		metodiService = factory.getMetodiService();
	}

	@Override	//inizializza la Carta o il conto e tutte le Label e i Text in base al Metodo di pagamento selezionato
	public void initializeData(MetodoDiPagamento metodo) {
		this.metodo = metodo;
		if (metodo instanceof Carta) {
			carta = (Carta) metodo;
			nomeText.setVisible(true);
			numeroText.setText("Numero");
			numeroText.setVisible(true);
			intestatarioText.setVisible(true);
			scadenzaText.setVisible(true);
			numeroLabel.setText(Long.toString(carta.getNumero()));
			intestatarioLabel.setText(carta.getIntestatario());
			nomeLabel.setText(carta.getNome());
			scadenzaLabel.setText(carta.getScadenza().format(DateTimeFormatter.ofPattern("MM/yy")));
		}

		if (metodo instanceof Conto) {
			conto = (Conto) metodo;
			nomeText.setVisible(true);
			numeroText.setText("Iban");
			numeroText.setVisible(true);
			intestatarioText.setVisible(true);
			nomeLabel.setText(conto.getNome());
			numeroLabel.setText(conto.getIban());
			intestatarioLabel.setText(conto.getIntestatario());
		}
	}

	@FXML	//si attiva con il pulsante Elimine, elimina il metodo di pagamento e chiude la pagina
	public void deleteAction(ActionEvent event) {
		try {
			metodiService.deleteMetodo(metodo);
			dispatcher.closeWindowView();
			dispatcher.renderView("profilo", metodo.getUtente());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}

	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}