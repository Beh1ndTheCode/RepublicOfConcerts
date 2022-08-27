package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class GestioneConcertiController implements DataInitializable<Concerto> {

	@FXML
	private TableView<Concerto> concertiTableView;

	@FXML
	private TableColumn<Concerto, String> artistaTableColumn, cittaTableColumn;

	@FXML
	private TableColumn<Concerto, String> dataTableColumn;

	@FXML
	private TableColumn<Concerto, Button> azioniTableColumn;

	private ViewDispatcher dispatcher;

	private ConcertoService concertiService;

	public GestioneConcertiController() {
		dispatcher = ViewDispatcher.getInstance();
		concertiService = new RAMConcertoServiceImpl();
	}

	public void initialize() {
		artistaTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getArtista());
		});
		cittaTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getLuogo().getCitta());
		});
		/*
		 * dataTableColumn.setCellFactory((CellDataFeatures<Concerto, String> param) ->
		 * { return new SimpleStringProperty((param.getValue().getData().toString()));
		 * });
		 */
		azioniTableColumn.setCellValueFactory((CellDataFeatures<Concerto, Button> param) -> {
			final Button infoButton = new Button("info");
			infoButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("infoConcerto", param.getValue());
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
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

	public void openAggiungiConcertoWindow(ActionEvent event) throws Exception {
		dispatcher.openNewWindow("aggiungiconcerto");
	}

}