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

public class TourGestioneConcertiController implements DataInitializable<Tour>{
	
	@FXML
	private ListView<Concerto> allConcertiListView, tourConcertiListView;
	
	@FXML
	private Button aggiungiButton;
	
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
		allConcertiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tourConcertiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		try {
			List<Concerto> concerti = concertoService.findAllConcerti();
			ObservableList<Concerto> concertiData = FXCollections.observableArrayList(concerti);
			allConcertiListView.setItems(concertiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void initializeData(Tour tour) {
		this.tour = tour;
		List<Concerto> concerti = tour.getConcerti();
		if(!(concerti == null)) {
			ObservableList<Concerto> concertiData = FXCollections.observableArrayList(concerti);
			tourConcertiListView.setItems(concertiData);
		}
	}
	
	public void blockAggiungiButton() {
		if(!(allConcertiListView.getSelectionModel().getSelectedItems() == null))
			aggiungiButton.setDisable(false);
	}
	
	public void addConcertiAction(ActionEvent event) {
		//tourService.addConcerto(tour, allConcertiListView.getSelectionModel().getSelectedItems());
		tour.setConcerti(allConcertiListView.getSelectionModel().getSelectedItems());
		initializeData(tour);
		aggiungiButton.setDisable(true);
	}
}
