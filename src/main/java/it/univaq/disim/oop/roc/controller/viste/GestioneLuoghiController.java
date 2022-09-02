package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Teatro;
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

public class GestioneLuoghiController implements DataInitializable<Luogo> {

	@FXML
	private TableView<Luogo> luoghiTableView;

	@FXML
	private TableColumn<Luogo, String> tipoTableColumn, cittaTableColumn, nomeTableColumn, capienzaTableColumn;

	@FXML
	private TableColumn<Luogo, Button> modificaTableColumn, settoriTableColumn;

	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;

	public GestioneLuoghiController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		tipoTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			if (param.getValue() instanceof Teatro)
				return new SimpleStringProperty("Teatro");
			else
				return new SimpleStringProperty("Stadio");
		});
		cittaTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			return new SimpleStringProperty(param.getValue().getCitta());
		});
		nomeTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
		capienzaTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			return new SimpleStringProperty(param.getValue().getCapienza().toString());
		});
		modificaTableColumn.setCellValueFactory((CellDataFeatures<Luogo, Button> param) -> {
			final Button infoButton = new Button("Modifica");
			infoButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("infoluogo", param.getValue());
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});
		settoriTableColumn.setCellValueFactory((CellDataFeatures<Luogo, Button> param) -> {
			final Button infoButton = new Button("Settori");
			infoButton.setOnAction(e -> dispatcher.renderView("gestionesettori", param.getValue()));
			return new SimpleObjectProperty<Button>(infoButton);
		});

		try {
			List<Luogo> luoghi = luoghiService.findAllLuoghi();
			ObservableList<Luogo> luoghiData = FXCollections.observableArrayList(luoghi);
			luoghiTableView.setItems(luoghiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void openAggiungiLuogoWindow(ActionEvent event) throws Exception {
		dispatcher.openNewWindow("aggiungiluogo");
	}

}
