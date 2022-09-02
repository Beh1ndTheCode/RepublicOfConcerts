package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class GestioneSettoriController implements DataInitializable<Luogo> {

	@FXML
	private TableView<Settore> settoriTableView;

	@FXML
	private TableColumn<Settore, String> nomeTableColumn, capienzaTableColumn;

	@FXML
	private TableColumn<Settore, Button> infoTableColumn;

	@FXML
	private Button aggiungiButton;

	@FXML
	private Label capienzaRimanenteLabel, luogoLabel;

	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;

	private Luogo luogo;

	public GestioneSettoriController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		nomeTableColumn.setCellValueFactory((CellDataFeatures<Settore, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
		capienzaTableColumn.setCellValueFactory((CellDataFeatures<Settore, String> param) -> {
			return new SimpleStringProperty(param.getValue().getCapienza().toString());
		});

		infoTableColumn.setCellValueFactory((CellDataFeatures<Settore, Button> param) -> {
			final Button infoButton = new Button("Info");
			infoButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("infosettore", param.getValue());
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});
	}

	public void initializeData(Luogo luogo) {
		this.luogo = luogo;
		capienzaRimanenteLabel.setText(luoghiService.getCapienzaRimanente(luogo).toString());
		luogoLabel.setText(luogo.toString());
		try {
			List<Settore> settori = luoghiService.findAllSettori(luogo);
			ObservableList<Settore> settoriData = FXCollections.observableArrayList(settori);
			settoriTableView.setItems(settoriData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void openAggiungiSettoreWindow(ActionEvent event) throws Exception {
		dispatcher.openNewWindow("aggiungisettori", luogo);
	}
}
