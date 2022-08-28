package it.univaq.disim.oop.roc.controller.finestre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
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
import javafx.scene.control.TextField;

public class AggiungiConcertoController implements DataInitializable<Concerto> {

	@FXML
	private TextField artistaTextField, dataTextField;

	@FXML
	private TableView<Luogo> luoghiTableView;

	@FXML
	private TableColumn<Luogo, String> nomeTableColumn, cittaTableColumn;

	@FXML
	private TableColumn<Luogo, Button> azioniTableColumn;

	@FXML
	private Button aggiungiConcertoButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private LuogoService luoghiService;

	private Luogo luogo;

	public AggiungiConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		aggiungiConcertoButton.setDisable(true);
		cittaTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			return new SimpleStringProperty(param.getValue().getCitta());
		});
		nomeTableColumn.setCellValueFactory((CellDataFeatures<Luogo, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNome());
		});
		azioniTableColumn.setCellValueFactory((CellDataFeatures<Luogo, Button> param) -> {
			final Button selectButton = new Button("seleziona");
			selectButton.setOnAction(e -> {
				luogo = luoghiTableView.getSelectionModel().getSelectedItem();
			});
			return new SimpleObjectProperty<Button>(selectButton);
		});

		try {
			List<Luogo> luoghi = luoghiService.findAllLuoghi();
			ObservableList<Luogo> luoghiData = FXCollections.observableArrayList(luoghi);
			luoghiTableView.setItems(luoghiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void blockAggiungiButton() {
		String artista = artistaTextField.getText();
		String data = dataTextField.getText();
		boolean isDisable = artista.isEmpty() || data.isEmpty();
		aggiungiConcertoButton.setDisable(isDisable);
	}

	public void addConcertoAction(ActionEvent event) {
		// try {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = dataTextField.getText();
		LocalDate localDate = LocalDate.parse(date, formatter);

		concertoService.addConcerto(artistaTextField.getText(), luogo, localDate);

		artistaTextField.setText("");
		dataTextField.setText("");
		blockAggiungiButton();
		dispatcher.renderView("gestioneconcerti");
		// } catch (BusinessException e) {
		// dispatcher.renderError(e);
		// }
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}

}
