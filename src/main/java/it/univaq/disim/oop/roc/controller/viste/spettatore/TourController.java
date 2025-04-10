package it.univaq.disim.oop.roc.controller.viste.spettatore;

import java.util.List;

import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class TourController implements DataInitializable<Utente> {

	@FXML
	private TableView<Tour> tourTableView;

	@FXML
	private TableColumn<Tour, String> artistaTableColumn, nomeTableColumn;

	@FXML
	private TableColumn<Tour, Button> modificaTableColumn;

	private ViewDispatcher dispatcher;

	private TourService tourService;

	public TourController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		tourService = factory.getTourService();
	}
	
	// creazione colonne Artista e Nome
	public void initialize() {
		artistaTableColumn.setCellValueFactory((CellDataFeatures<Tour, String> param) -> {
			return new SimpleStringProperty(param.getValue().getArtista());
		});
		nomeTableColumn.setCellValueFactory((CellDataFeatures<Tour, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
	}

	@Override	//creazione colonna con Bottone Info e ricerca di tutti i Tour
	public void initializeData(Utente utente) {
		modificaTableColumn.setCellValueFactory((CellDataFeatures<Tour, Button> param) -> {
			final Button infoButton = new Button("Info");
			infoButton.setOnAction(e -> {
				dispatcher.renderView("infotour", param.getValue(), utente);
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});

		try {
			List<Tour> tour = tourService.findAllTours();
			ObservableList<Tour> tourData = FXCollections.observableArrayList(tour);
			tourTableView.setItems(tourData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}
