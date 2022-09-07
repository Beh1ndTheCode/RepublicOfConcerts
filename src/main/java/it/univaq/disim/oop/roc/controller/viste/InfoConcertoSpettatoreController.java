package it.univaq.disim.oop.roc.controller.viste;

import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.TipologiaMetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoConcertoSpettatoreController implements DataInitializable<Concerto>, UtenteInitializable<Utente> {

	@FXML
	private Label artistiLabel, scalettaLabel, luogoLabel, tourLabel, metodoLabel, dataLabel;

	@FXML
	private Text metodoText;

	@FXML
	private Button prenotaButton;

	private ViewDispatcher dispatcher;

	private Concerto concerto;

	private Utente utente;

	public InfoConcertoSpettatoreController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initialize() {

	}

	@Override
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;

		if (concerto.getTipoMetodo() == TipologiaMetodoDiPagamento.Carta) {
			metodoText.setText("Metodo di pagamento");
			metodoLabel.setText("Carta");

		} else if (concerto.getTipoMetodo() == TipologiaMetodoDiPagamento.Conto) {
			metodoText.setText("Metodo di pagamento");
			metodoLabel.setText("Bonifico");
		} else {
			metodoText.setText("Metodi di pagamento");
			metodoLabel.setText("Carta, Bonifico");
		}

		if (!(concerto.getScaletta() == null))
			scalettaLabel.setText(concerto.getScaletta());

		if (!(concerto.getTour() == null))
			tourLabel.setText(concerto.getTour().toString());

		else
			tourLabel.setText("nessuno");

		artistiLabel.setText(concerto.getArtista());
		scalettaLabel.setText(concerto.getScaletta());
		dataLabel.setText(concerto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		luogoLabel.setText(concerto.getLuogo().toString());

	}

	@Override
	public void initializeUtente(Utente utente) {
		this.utente = utente;
	}

	@FXML
	public void prenotaBigliettoAction() throws ViewException {
		dispatcher.openNewWindow("prenotabiglietto", concerto, utente);
	}

}
