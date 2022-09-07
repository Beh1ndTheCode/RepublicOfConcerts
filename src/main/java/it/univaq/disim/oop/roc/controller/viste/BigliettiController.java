package it.univaq.disim.oop.roc.controller.viste;

import java.time.format.DateTimeFormatter;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Spettatore;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class BigliettiController implements DataInitializable<Concerto>, UtenteInitializable<Utente>{
	
	@FXML
	private TableView<Biglietto> bigliettiTableView;
	
	@FXML
	private TableColumn<Biglietto, String> tariffaTableColumn, numeroBigliettoTableColumn, postoTableColumn;
	
	@FXML
	private TableColumn<Biglietto, Button> cambiaPostoTableColumn;
	
	@FXML
	private Label concertoLabel;
	
	private ViewDispatcher dispatcher;

	private BigliettoService bigliettoService;
	
	private Spettatore spettatore;
	
	private Concerto concerto;
	
	public BigliettiController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		bigliettoService = factory.getBigliettoService();
	}
	
	public void initialize() {
		tariffaTableColumn.setCellValueFactory((CellDataFeatures<Biglietto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getTariffa().toString());
		});

		numeroBigliettoTableColumn.setCellValueFactory((CellDataFeatures<Biglietto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getNumeroBiglietto().toString());
		});
		
		postoTableColumn.setCellValueFactory((CellDataFeatures<Biglietto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getPosto().toString());
		});
	}
	
	public void initializeUtente(Utente utente) {
		this.spettatore = (Spettatore) utente;
		cambiaPostoTableColumn.setCellValueFactory((CellDataFeatures<Biglietto, Button> param) -> {
			final Button prenotaButton = new Button("Cambia Posto");
			prenotaButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("cambiaposto", param.getValue(), spettatore);
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(prenotaButton);
		});
	}
	
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;
		
		try {
			List<Biglietto> biglietti = bigliettoService.findBigliettiByConcertoAndSpettatore(concerto, spettatore);
			ObservableList<Biglietto> bigliettiData = FXCollections.observableArrayList(biglietti);
			bigliettiTableView.setItems(bigliettiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}
