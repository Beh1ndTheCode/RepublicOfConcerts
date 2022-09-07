package it.univaq.disim.oop.roc.controller.viste;
import java.time.format.DateTimeFormatter;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class InfoTourController implements DataInitializable<Tour>, UtenteInitializable<Utente> {

	@FXML
	private TableView<Concerto> concertiTableView;

	@FXML
	private TableColumn<Concerto, String> luogoTableColumn, dataTableColumn;

	@FXML
	private TableColumn<Concerto, Button> prenotaTableColumn;
	
	@FXML
	private Label tourLabel, artistaLabel;

	private ViewDispatcher dispatcher;

	private ConcertoService concertiService;
	
	private Tour tour;
	
	private Utente utente;

	public InfoTourController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		concertiService = factory.getConcertoService();
	}

	public void initialize() {
		luogoTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getLuogo().toString());
		});

		dataTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			String data = param.getValue().getData().format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
			return new SimpleStringProperty(data);
		});
	}
	
	public void initializeUtente(Utente utente) {
		prenotaTableColumn.setCellValueFactory((CellDataFeatures<Concerto, Button> param) -> {
			final Button prenotaButton = new Button("Prenota");
			prenotaButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("prenotabiglietto", param.getValue(),utente);
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(prenotaButton);
		});
	}
	
	public void initializeData(Tour tour) {
		this.tour = tour;
		tourLabel.setText(tourLabel.getText() + " " + tour.getNome());
		artistaLabel.setText(tour.getArtista());
		try {
			List<Concerto> concerti = concertiService.findConcertiByTour(tour);
			ObservableList<Concerto> concertiData = FXCollections.observableArrayList(concerti);
			concertiTableView.setItems(concertiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	
}