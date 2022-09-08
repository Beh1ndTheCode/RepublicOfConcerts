package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InfoTourController implements DataInitializable<Tour> {

	@FXML
	private Label artistaLabel;

	@FXML
	private TextField nomeTextField;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	private Tour tour;

	public InfoTourController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		tourService = factory.getTourService();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}

	@Override
	public void initializeData(Tour tour) {
		this.tour = tour;
		nomeTextField.setPromptText(tour.getNome());
		artistaLabel.setText(tour.getArtista());
	}

	public void blockModificaButton() {
		String nome = nomeTextField.getText();
		boolean isDisable = nome.isEmpty();
		modificaButton.setDisable(isDisable);
	}

	@FXML
	public void updateTourAction(ActionEvent event) {
		try {
			if (!nomeTextField.getText().isEmpty())
				tour.setNome(nomeTextField.getText());
			tourService.updateTour(tour);

			initializeData(tour);
			nomeTextField.setText("");
			blockModificaButton();
			dispatcher.renderView("gestionetour");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void deleteTourAction(ActionEvent event) {
		try {
			tourService.deleteTour(tour);
			closeWindow();
			dispatcher.renderView("gestionetour");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
