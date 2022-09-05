package it.univaq.disim.oop.roc.controller.viste;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
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

public class ConcertiController implements DataInitializable<Utente> {

	@FXML
	private TableView<Concerto> concertiTableView;

	@FXML
	private TableColumn<Concerto, String> artistaTableColumn, luogoTableColumn;

	@FXML
	private TableColumn<Concerto, String> dataTableColumn;

	@FXML
	private TableColumn<Concerto, Button> azioniTableColumn;

	private ViewDispatcher dispatcher;

	private ConcertoService concertiService;
	
	private Utente utente;

	public ConcertiController() {
		dispatcher = ViewDispatcher.getInstance();
		concertiService = new RAMConcertoServiceImpl();
	}

	public void initialize() {
		artistaTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getArtista());
		});
		luogoTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getLuogo().toString());
		});

		dataTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			String data = param.getValue().getData().format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
			return new SimpleStringProperty(data);
		});
	}

	public void initializeData(Utente utente) {
		this.utente = utente;
		azioniTableColumn.setCellValueFactory((CellDataFeatures<Concerto, Button> param) -> {
			final Button infoButton = new Button("Info");
			infoButton.setOnAction(e -> {
				dispatcher.renderView("infoconcertospettatore", param.getValue(), this.utente);
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});

		try {
			List<Concerto> concerti = concertiService.findAllConcerti();
			ObservableList<Concerto> concertiData = FXCollections.observableArrayList(concerti);
			concertiTableView.setItems(concertiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}