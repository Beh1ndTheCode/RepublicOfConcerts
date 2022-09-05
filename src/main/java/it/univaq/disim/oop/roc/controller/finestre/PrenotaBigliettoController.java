package it.univaq.disim.oop.roc.controller.finestre;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMMetodiServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMTariffeServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class PrenotaBigliettoController implements DataInitializable<Concerto>, UtenteInitializable<Utente> {

	@FXML
	private ListView<Tariffa> settoriListView;

	@FXML
	private ListView<MetodoDiPagamento> metodiListView;

	@FXML
	private Text metodoText;

	@FXML
	private Label settoreLabel, settoreErrorLabel, metodoLabel, metodoErrorLabel, prezzoInteroLabel, prezzoRidottoLabel;

	@FXML
	private Button compraButton;

	private ConcertoService concertoService;

	private TariffeService tariffeService;

	private MetodiService metodiService;

	private ViewDispatcher dispatcher;

	private Concerto concerto;

	private Utente utente;

	private TipoMetodoDiPagamento tipoMetodo;

	public PrenotaBigliettoController() {
		dispatcher = ViewDispatcher.getInstance();
		metodiService = new RAMMetodiServiceImpl();
		tariffeService = new RAMTariffeServiceImpl();
		concertoService = new RAMConcertoServiceImpl();
		// metodiService = new FileMetodiServiceImpl();
		// tariffeService = new FileTariffeServiceImpl();
		// concertoService = new FileConcertoServiceImpl();
	}

	public void initialize() {
		compraButton.setDisable(true);
	}

	public void initializeData(Concerto concerto) {
		this.concerto = concerto;
		this.tipoMetodo = concerto.getTipoMetodo();
		if (tipoMetodo == TipoMetodoDiPagamento.Carta) {
			metodoText.setText("Le tue carte");
		} else if (tipoMetodo == TipoMetodoDiPagamento.Conto) {
			metodoText.setText("I tuoi conti");
		} else {
			metodoText.setText("I tuoi metodi di pagamento");
		}
		try {
			List<Tariffa> tariffe = tariffeService.findAllTariffe(concerto);
			ObservableList<Tariffa> tariffeData = FXCollections.observableArrayList(tariffe);
			settoriListView.setItems(tariffeData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void initializeUtente(Utente utente) {
		this.utente = utente;
		List<MetodoDiPagamento> metodi;
		try {
			if (tipoMetodo == TipoMetodoDiPagamento.Carta) {
				metodi = metodiService.findAllCarte(utente);
			} else if (tipoMetodo == TipoMetodoDiPagamento.Conto) {
				metodi = metodiService.findAllConti(utente);
			} else {
				metodi = metodiService.findAllMetodi(utente);
			}
			ObservableList<MetodoDiPagamento> metodiData = FXCollections.observableArrayList(metodi);
			metodiListView.setItems(metodiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void settoreSelezionato() {
		try {
			if (settoriListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			settoreErrorLabel.setText(null);
			settoreLabel.setText(settoriListView.getSelectionModel().getSelectedItem().toString());
			prezzoInteroLabel
					.setText(settoriListView.getSelectionModel().getSelectedItem().getPrezzoIntero().toString() + "€");
			prezzoRidottoLabel
					.setText(settoriListView.getSelectionModel().getSelectedItem().getPrezzoRidotto().toString() + "€");
			BlockCompraButton();
		} catch (SelectionException e) {
			settoreErrorLabel.setText("Seleziona un settore");
		}
	}

	public void metodoSelezionato() {
		try {
			if (metodiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			metodoErrorLabel.setText(null);
			metodoLabel.setText(metodiListView.getSelectionModel().getSelectedItem().toString());
			BlockCompraButton();
		} catch (SelectionException e) {
			if (tipoMetodo == TipoMetodoDiPagamento.Carta) {
				metodoErrorLabel.setText("Seleziona una carta");
				if (tipoMetodo == TipoMetodoDiPagamento.Conto)
					metodoErrorLabel.setText("Seleziona un conto");
			} else
				metodoErrorLabel.setText("Seleziona un metodo di pagamento");
		}
	}

	public void BlockCompraButton() {
		if (metodoLabel != null && metodoLabel != null) {
			compraButton.setDisable(false);
		}
	}

	public void CompraBigliettoAction(ActionEvent event) throws BusinessException {
		try {
			Biglietto biglietto = new Biglietto();
			biglietto.setConcerto(concerto);
			biglietto.setUtente(utente);
			biglietto.setTariffa(settoriListView.getSelectionModel().getSelectedItem());
			concertoService.prenotaBiglietto(biglietto);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
