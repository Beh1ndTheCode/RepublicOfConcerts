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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InfoTourController implements DataInitializable<Tour> {

	@FXML
	private Text nomeText, artistaText;

	@FXML
	private Label nomeLabel, artistaLabel;

	@FXML
	private TextField nomeTextField, artistaTextField;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	private Tour tour;

	public InfoTourController() {
		dispatcher = ViewDispatcher.getInstance();
		tourService = new RAMTourServiceImpl();
		// tourService = new FileTourServiceImpl();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}

	public void initializeData(Tour tour) {
		this.tour = tour;
		nomeLabel.setText(tour.getNome());
		nomeTextField.setPromptText(tour.getNome());
		artistaLabel.setText(tour.getArtista());
		artistaTextField.setPromptText(tour.getArtista());
	}

	public void blockModificaButton() {
		String nome = nomeTextField.getText();
		String artista = artistaTextField.getText();
		boolean isDisable = nome.isEmpty() && artista.isEmpty();
		modificaButton.setDisable(isDisable);
	}

	public void updateTourAction(ActionEvent event) {
		try {
			if (!artistaTextField.getText().isEmpty())
				tour.setArtista(artistaTextField.getText());
			if (!nomeTextField.getText().isEmpty())
				tour.setNome(nomeTextField.getText());
			tourService.updateTour(tour);

			initializeData(tour);
			nomeTextField.setText("");
			artistaTextField.setText("");
			blockModificaButton();
			dispatcher.renderView("gestionetour");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

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
