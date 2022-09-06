package it.univaq.disim.oop.roc.controller.finestre;

import java.time.LocalDate;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.Utility;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AggiungiConcertoController implements DataInitializable<Concerto> {

	@FXML
	private TextField artistaTextField, giornoTextField, meseTextField, annoTextField;

	@FXML
	private ListView<Luogo> luoghiListView;

	@FXML
	private Label luogoLabel, dataErrorLabel, luogoErrorLabel;

	@FXML
	private Button aggiungiConcertoButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private TariffeService tariffeService;

	private LuogoService luoghiService;

	private Luogo luogo;

	public AggiungiConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		concertoService = factory.getConcertoService();
		luoghiService = factory.getLuogoService();
		tariffeService = factory.getTariffeService();
	}

	public void initialize() {
		aggiungiConcertoButton.setDisable(true);
		try {
			List<Luogo> luoghi = luoghiService.findAllLuoghi();
			ObservableList<Luogo> luoghiData = FXCollections.observableArrayList(luoghi);
			luoghiListView.setItems(luoghiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void luogoSelezionato() {
		try {
			if (luoghiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			luogoErrorLabel.setText(null);
			luogoLabel.setText(luoghiListView.getSelectionModel().getSelectedItem().toString());
		} catch (SelectionException e) {
			luogoErrorLabel.setText("Seleziona un luogo");
		}
	}

	public void blockAggiungiButton() {
		String artista = artistaTextField.getText();
		String giorno = giornoTextField.getText();
		String mese = meseTextField.getText();
		String anno = annoTextField.getText();

		boolean isDisable = artista.isEmpty() || giorno.isEmpty() || mese.isEmpty() || anno.isEmpty();
		aggiungiConcertoButton.setDisable(isDisable);
	}

	public void addConcertoAction(ActionEvent event) {
		try {
			if (luoghiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();

			Concerto concerto = new Concerto();
			LocalDate data = Utility.VerificaData(giornoTextField.getText(), meseTextField.getText(),
					annoTextField.getText());
			concerto.setArtista(artistaTextField.getText());
			concerto.setLuogo(luoghiListView.getSelectionModel().getSelectedItem());
			concerto.setData(data);
			concerto = concertoService.addConcerto(concerto);
			luogo = luoghiListView.getSelectionModel().getSelectedItem();
			tariffeService.addTariffe(concerto, luogo);

			luoghiListView.getSelectionModel().clearSelection();
			luogoLabel.setText("");
			dataErrorLabel.setText("");
			luogoErrorLabel.setText("");
			artistaTextField.setText("");
			giornoTextField.setText("");
			meseTextField.setText("");
			annoTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestioneconcerti");
		} catch (SelectionException e) {
			luogoErrorLabel.setText("Inserisci un luogo");
		} catch (IntegerFormatException e) {
			dataErrorLabel.setText("Inserisci una data valida");
		} catch (InvalidDateException e) {
			dataErrorLabel.setText("Inserisci una data valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}

}
