package it.univaq.disim.oop.roc.controller.finestre.amministratore;

import java.util.Set;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
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

public class AggiungiTourController implements DataInitializable<Object> {

	@FXML
	private ListView<String> artistiListView;

	@FXML
	private TextField nomeTextField;

	@FXML
	private Label artistaLabel, artistaErrorLabel;

	@FXML
	private Button aggiungiButton;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	private ConcertoService concertoService;

	public AggiungiTourController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		tourService = factory.getTourService();
		concertoService = factory.getConcertoService();
	}

	public void initialize() {
		aggiungiButton.setDisable(true);
		try {
			Set<String> artisti = concertoService.findAllArtisti();
			ObservableList<String> artistiData = FXCollections.observableArrayList(artisti);
			artistiListView.setItems(artistiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void luogoSelezionato() {
		try {
			if (artistiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			artistaErrorLabel.setText(null);
			artistaLabel.setText(artistiListView.getSelectionModel().getSelectedItem().toString());
		} catch (SelectionException e) {
			artistaErrorLabel.setText("Seleziona un artista");
		}
	}

	public void blockAggiungiButton() {
		String nome = nomeTextField.getText();
		boolean isDisable = nome.isEmpty();
		aggiungiButton.setDisable(isDisable);
	}

	@FXML
	public void addTourAction(ActionEvent event) {
		try {
			if (artistiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			Tour tour = new Tour();
			tour.setArtista(artistiListView.getSelectionModel().getSelectedItem());
			tour.setNome(nomeTextField.getText());
			tourService.addTour(tour);

			artistiListView.getSelectionModel().clearSelection();
			nomeTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestionetour");
		} catch (SelectionException e) {
			artistaErrorLabel.setText("Inserisci un artista");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
