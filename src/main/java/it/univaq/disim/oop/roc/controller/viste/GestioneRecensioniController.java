package it.univaq.disim.oop.roc.controller.viste;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.impl.ram.RAMRocBusinessFactoryImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
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

public class GestioneRecensioniController implements DataInitializable<Object> {
	
	@FXML
	private TableView<Recensione> recensioniTableView;
	
	@FXML
	private TableColumn<Recensione, String> valutazioneTableColumn, titoloTableColumn, concertoTableColumn, approvatoTableColumn;
	
	@FXML
	private TableColumn<Recensione, Button> modificaTableColumn;
	
	private ViewDispatcher dispatcher;
	
	private RecensioniService recensioniService;
	
	public GestioneRecensioniController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = new RAMRocBusinessFactoryImpl();
		recensioniService = factory.getRecensioniService();
	}
	
	public void initialize() {
		valutazioneTableColumn.setCellValueFactory((CellDataFeatures<Recensione, String> param) -> {
			return new SimpleStringProperty(param.getValue().getValutazione().toString());
		});
		titoloTableColumn.setCellValueFactory((CellDataFeatures<Recensione, String> param) -> {
			return new SimpleStringProperty(param.getValue().getTitolo());
		});

		concertoTableColumn.setCellValueFactory((CellDataFeatures<Recensione, String> param) -> {
			return new SimpleStringProperty(param.getValue().getConcerto().toString());
		});
		modificaTableColumn.setCellValueFactory((CellDataFeatures<Recensione, Button> param) -> {
			final Button infoButton = new Button("Modifica");
			infoButton.setOnAction(e -> {
				dispatcher.renderView("modificarecensione", param.getValue());
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});
		try {
			List<Recensione> recensioni = recensioniService.findRecensioniInAttesa();
			ObservableList<Recensione> recensioniData = FXCollections.observableArrayList(recensioni);
			recensioniTableView.setItems(recensioniData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
