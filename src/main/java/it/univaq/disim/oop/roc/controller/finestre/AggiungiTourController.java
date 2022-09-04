package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMTourServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AggiungiTourController implements DataInitializable<Object> {

	@FXML
	private TextField nomeTextField, artistaTextField;

	@FXML
	private Button aggiungiButton;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	public AggiungiTourController() {
		dispatcher = ViewDispatcher.getInstance();
		tourService = new RAMTourServiceImpl();
		// tourService = new FileTourServiceImpl();
	}

	public void initialize() {
		aggiungiButton.setDisable(true);
	}

	public void blockAggiungiButton() {
		String nome = nomeTextField.getText();
		String artista = artistaTextField.getText();
		boolean isDisable = nome.isEmpty() || artista.isEmpty();
		aggiungiButton.setDisable(isDisable);
	}

	public void addTourAction(ActionEvent event) {
		try {
			Tour tour = new Tour();
			tour.setArtista(artistaTextField.getText());
			tour.setNome(nomeTextField.getText());
			tourService.addTour(tour);

			nomeTextField.setText("");
			artistaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestionetour");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
