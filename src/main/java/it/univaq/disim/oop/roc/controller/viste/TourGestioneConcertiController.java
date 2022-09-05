package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMTourServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class TourGestioneConcertiController implements DataInitializable<Tour> {

	@FXML
	private ListView<Concerto> allConcertiArtistaListView, tourConcertiListView;

	@FXML
	private Button aggiungiButton, eliminaConcertiButton;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	private ConcertoService concertoService;

	private Tour tour;

	public TourGestioneConcertiController() {
		dispatcher = ViewDispatcher.getInstance();
		tourService = new RAMTourServiceImpl();
		concertoService = new RAMConcertoServiceImpl();
	}

	public void initialize() {
		aggiungiButton.setDisable(true);
		eliminaConcertiButton.setDisable(true);
		allConcertiArtistaListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tourConcertiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void initializeData(Tour tour) {
		this.tour = tour;
		try {
			List<Concerto> concertiArtista = concertoService.findConcertiByArtista(tour.getArtista());
			ObservableList<Concerto> concertiArtistaData = FXCollections.observableArrayList(concertiArtista);
			allConcertiArtistaListView.setItems(concertiArtistaData);

			List<Concerto> concertiTour = concertoService.findConcertiByTour(tour);
			if (!(concertiTour == null)) {
				ObservableList<Concerto> concertiTourData = FXCollections.observableArrayList(concertiTour);
				tourConcertiListView.setItems(concertiTourData);
			}
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void blockAggiungiButton() {
		if (!(allConcertiArtistaListView.getSelectionModel().getSelectedItems() == null))
			aggiungiButton.setDisable(false);
	}

	public void blockEliminaConcertiAction() {
		if (!(tourConcertiListView.getSelectionModel().getSelectedItems() == null))
			eliminaConcertiButton.setDisable(false);
	}

	public void addConcertiAction(ActionEvent event) {
		try {
			tourService.addConcerti(tour, allConcertiArtistaListView.getSelectionModel().getSelectedItems());
			allConcertiArtistaListView.getSelectionModel().clearSelection();
			initializeData(tour);
			aggiungiButton.setDisable(true);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void deleteConcertiAction(ActionEvent event) {
		try {
			tourService.deleteConcerti(tour, tourConcertiListView.getSelectionModel().getSelectedItems());
			tourConcertiListView.getSelectionModel().clearSelection();
			initializeData(tour);
			eliminaConcertiButton.setDisable(true);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
