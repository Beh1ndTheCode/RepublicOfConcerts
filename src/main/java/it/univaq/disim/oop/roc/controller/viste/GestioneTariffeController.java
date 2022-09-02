package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
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
import javafx.scene.control.TextField;

public class GestioneTariffeController implements DataInitializable<Concerto>{
	
	@FXML
	private TableView<Settore> settoriTableView;
	
	@FXML
	private TableColumn<Settore, String> settoreTableColumn, tariffaTableColumn;
	
	@FXML
	private TableColumn<Settore, Button> modificaTableColumn;
	
	@FXML
	private Button modificaButton;
	
	@FXML
	private Label settoreLabel, errorLabel, concertoLabel;
	
	@FXML
	private TextField tariffaTextField;
	
	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;
	
	private ConcertoService concertoService;
	
	private Concerto concerto;
	
	private Settore settore;
	
	public GestioneTariffeController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
		concertoService = new RAMConcertoServiceImpl();
	}
	
	public void initialize() {
		settoreTableColumn.setCellValueFactory((CellDataFeatures<Settore, String> param) -> {
			return new SimpleStringProperty(param.getValue().toString());
		});
		tariffaTableColumn.setCellValueFactory((CellDataFeatures<Settore, String> param) -> {
			return new SimpleStringProperty(param.getValue().getTariffa().toString());
		});
		modificaTableColumn.setCellValueFactory((CellDataFeatures<Settore, Button> param) -> {
			final Button infoButton = new Button("Modifica");
			infoButton.setOnAction(e -> {
				settoreLabel.setText(param.getValue().toString());
				this.settore = param.getValue();
			});
			return new SimpleObjectProperty<Button>(infoButton);
		});
	}
	
	public void initializeData(Concerto concerto) {
		modificaButton.setDisable(true);
		this.concerto = concerto;
		concertoLabel.setText(concerto.toString());
		try {
			List<Settore> settori = luoghiService.findAllSettori(concerto.getLuogo());
			ObservableList<Settore> settoriData = FXCollections.observableArrayList(settori);
			settoriTableView.setItems(settoriData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	public void BlockModificaButton() {
		String tariffa = tariffaTextField.getText();
		boolean isDisable = tariffa.isEmpty();
		modificaButton.setDisable(isDisable);
	}
	
	public void SetTariffa(ActionEvent event) {
		if(settore == null)
			errorLabel.setText("nessun settore selezionato");
		else {
			try {
				concertoService.setTariffa(settore,tariffaTextField.getText());
			} catch (FloatFormatException e) {
				errorLabel.setText("tariffa non valida");
			}
			dispatcher.renderView("gestionetariffe",concerto);
		}
	}
}
