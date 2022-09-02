package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMTariffeServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
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

public class GestioneTariffeController implements DataInitializable<Concerto> {

	@FXML
	private TableView<Tariffa> settoriTableView;

	@FXML
	private TableColumn<Tariffa, String> settoreTableColumn;

	@FXML
	private TableColumn<Tariffa, String> tariffaTableColumn;

	@FXML
	private TableColumn<Tariffa, Button> selezionaTableColumn;

	@FXML
	private Button modificaButton;

	@FXML
	private Label settoreLabel, errorLabel, concertoLabel;

	@FXML
	private TextField prezzoTextField;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private TariffeService tariffeService;

	private Concerto concerto;

	private Settore settore;

	private Tariffa tariffa;

	public GestioneTariffeController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		tariffeService = new RAMTariffeServiceImpl();
	}

	public void initialize() {
		settoreTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, String> param) -> {
			return new SimpleStringProperty(param.getValue().getSettore().toString());
		});

		tariffaTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, String> param) -> {
			return new SimpleStringProperty(param.getValue().getPrezzo().toString());
		});

		selezionaTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, Button> param) -> {
			final Button selezionaButton = new Button("Seleziona");
			selezionaButton.setOnAction(e -> {
				settoreLabel.setText(param.getValue().toString());
				this.tariffa = param.getValue();
			});
			return new SimpleObjectProperty<Button>(selezionaButton);
		});
	}

	public void initializeData(Concerto concerto) {
		modificaButton.setDisable(true);
		this.concerto = concerto;
		concertoLabel.setText(concerto.toString());
		try {
			List<Tariffa> tariffe = tariffeService.findTariffeByConcerto(concerto);
			ObservableList<Tariffa> tariffeData = FXCollections.observableArrayList(tariffe);
			settoriTableView.setItems(tariffeData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void BlockModificaButton() {
		String tariffa = prezzoTextField.getText();
		boolean isDisable = tariffa.isEmpty();
		modificaButton.setDisable(isDisable);
	}

	public void setTariffaAction(ActionEvent event) {
		if (settore == null)
			errorLabel.setText("nessun settore selezionato");
		else {
			try {
				tariffeService.setTariffa(concerto, settore, tariffa, prezzoTextField.getText());
			} catch (FloatFormatException e) {
				errorLabel.setText("tariffa non valida");
				return;
			} catch (BusinessException e) {
				dispatcher.renderError(e);
			}
			dispatcher.renderView("gestionetariffe", concerto);
		}
	}
}
