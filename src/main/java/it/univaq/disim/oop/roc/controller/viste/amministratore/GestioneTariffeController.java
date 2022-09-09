package it.univaq.disim.oop.roc.controller.viste.amministratore;

import java.util.List;

import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
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
import javafx.scene.text.Text;

public class GestioneTariffeController implements DataInitializable<Concerto> {

	@FXML
	private TableView<Tariffa> settoriTableView;

	@FXML
	private TableColumn<Tariffa, String> settoreTableColumn, tariffaTableColumn;

	@FXML
	private TableColumn<Tariffa, Button> selezionaTableColumn;

	@FXML
	private Button modificaButton;

	@FXML
	private Text settoreText;

	@FXML
	private Label errorLabel, concertoLabel;

	@FXML
	private TextField prezzoTextField;

	private ViewDispatcher dispatcher;

	private TariffeService tariffeService;

	private Concerto concerto;

	private Tariffa tariffa;

	public GestioneTariffeController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		tariffeService = factory.getTariffeService();
	}

	public void initialize() {
		settoreTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, String> param) -> {
			return new SimpleStringProperty(param.getValue().getSettore().getNome());
		});

		tariffaTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, String> param) -> {
			if (param.getValue().getPrezzoIntero() == null)
				return new SimpleStringProperty("");
			return new SimpleStringProperty(param.getValue().getPrezzoIntero().toString() + "€");
		});

		selezionaTableColumn.setCellValueFactory((CellDataFeatures<Tariffa, Button> param) -> {
			final Button selezionaButton = new Button("Seleziona");
			selezionaButton.setOnAction(e -> {
				settoreText.setText(param.getValue().getSettore().getNome());
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
			List<Tariffa> tariffe = tariffeService.findAllTariffe(concerto);
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

	public void tariffaSelezionata() {
		try {
			if (settoriTableView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			errorLabel.setText(null);
			settoreText.setText(settoriTableView.getSelectionModel().getSelectedItem().getSettore().getNome());
		} catch (SelectionException e) {
			errorLabel.setText("Nessun settore selezionato");
		}
	}

	public void setTariffaAction(ActionEvent event) {
		try {
			if (tariffa == null)
				throw new SelectionException();
			Float inputPrezzo;
			try {
				inputPrezzo = Float.parseFloat(prezzoTextField.getText().replace(",", "."));
				if (inputPrezzo < 0)
					throw new NumberFormatException();
				tariffa.setPrezzo(inputPrezzo);
			} catch (NumberFormatException n) {
				errorLabel.setText("Tariffa non valida");
			}
			tariffeService.setTariffa(tariffa);

			dispatcher.renderView("gestionetariffe", concerto);
		} catch (SelectionException e) {
			errorLabel.setText("Settore non selezionato");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

}
