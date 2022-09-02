package it.univaq.disim.oop.roc.controller.viste;

import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoConcertoSpettatoreController implements DataInitializable<Concerto> {

	@FXML
	private Label artistiLabel, scalettaLabel, luogoLabel, tourLabel, metodoLabel, dataLabel;

	@FXML
	private Button prenotaButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private LuogoService luoghiService;

	private Concerto concerto;

	private TipoMetodoDiPagamento tipoMetodo;

	public InfoConcertoSpettatoreController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		this.tipoMetodo = TipoMetodoDiPagamento.Carta;
	}

	public void initializeData(Concerto concerto) {
		this.concerto = concerto;

		tipoMetodo = concerto.getMetodo();
		if (tipoMetodo == TipoMetodoDiPagamento.Carta)
			metodoLabel.setText("Carta");

		if (tipoMetodo == TipoMetodoDiPagamento.Conto)
			metodoLabel.setText("Conto");

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

	public void prenotaBigliettoAction() {

	}

}
