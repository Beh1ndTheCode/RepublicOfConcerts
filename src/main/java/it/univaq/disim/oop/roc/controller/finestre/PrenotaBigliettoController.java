package it.univaq.disim.oop.roc.controller.finestre;

import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
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

	private TariffeService tariffeService;

	private MetodiService metodiService;

	private ViewDispatcher dispatcher;

	private Concerto concerto;

	public PrenotaBigliettoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		metodiService = factory.getMetodiService();
		tariffeService = factory.getTariffeService();
	}

	public void initialize() {
		compraButton.setDisable(true);
	}

	@Override
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;
		if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Carta) {
			metodoText.setText("Le tue carte");
		} else if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Conto) {
			metodoText.setText("I tuoi conti");
		} else {
			metodoText.setText("I tuoi metodi di pagamento");
		}
		try {
			List<Tariffa> tariffe = tariffeService.findAllTariffe(concerto);
			for (Tariffa fee : tariffe) {
				if (fee.getPrezzoIntero() == null)
					tariffe.remove(fee);
			}
			ObservableList<Tariffa> tariffeData = FXCollections.observableArrayList(tariffe);
			settoriListView.setItems(tariffeData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@Override
	public void initializeUtente(Utente utente) {
		List<MetodoDiPagamento> metodi;
		try {
			if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Carta) {
				metodi = metodiService.findAllCarte(utente);
			} else if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Conto) {
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

	@FXML
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

	@FXML
	public void metodoSelezionato() {
		try {
			if (metodiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			metodoErrorLabel.setText(null);
			metodoLabel.setText(metodiListView.getSelectionModel().getSelectedItem().toString());
			BlockCompraButton();
		} catch (SelectionException e) {
			if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Carta) {
				metodoErrorLabel.setText("Seleziona una carta");
			} else if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Conto) {
				metodoErrorLabel.setText("Seleziona un conto");
			} else {
				metodoErrorLabel.setText("Seleziona un metodo di pagamento");
			}
		}
	}

	public void BlockCompraButton() {
		if ((settoreLabel.getText() != "") && (metodoLabel.getText() != "")) {
			compraButton.setDisable(false);
		}
	}

	@FXML
	public void CompraBigliettoAction(ActionEvent event) throws BusinessException {
		try {
			dispatcher.closeWindowView();
			dispatcher.openNewWindow("checkout", settoriListView.getSelectionModel().getSelectedItem(),
					metodiListView.getSelectionModel().getSelectedItem());
		} catch (ViewException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
