package it.univaq.disim.oop.roc.controller.viste.spettatore;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class ITuoiConcertiController implements DataInitializable<Utente> {

	@FXML
	private TableView<Concerto> concertiTableView;

	@FXML
	private TableColumn<Concerto, String> artistaTableColumn, luogoTableColumn;

	@FXML
	private TableColumn<Concerto, String> dataTableColumn;

	@FXML
	private TableColumn<Concerto, Button> bigliettiTableColumn, recensioneTableColumn;

	private ViewDispatcher dispatcher;

	private BigliettoService bigliettoService;

	private ConcertoService concertoService;

	public ITuoiConcertiController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		bigliettoService = factory.getBigliettoService();
		concertoService = factory.getConcertoService();
	}

	// creazione colonne Artista, Luogo e Data
	public void initialize() {
		artistaTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getArtista());
		});
		luogoTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			return new SimpleStringProperty(param.getValue().getLuogo().toString());
		});

		dataTableColumn.setCellValueFactory((CellDataFeatures<Concerto, String> param) -> {
			String data = param.getValue().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return new SimpleStringProperty(data);
		});
	}

	@Override	//creazione colonne con bottoni Biglietti e Recensione
				//e ricerca dei concerti tramite i biglietti dell'utente
	public void initializeData(Utente utente) {
		bigliettiTableColumn.setCellValueFactory((CellDataFeatures<Concerto, Button> param) -> {
			final Button bigliettiButton = new Button("Biglietti");
			bigliettiButton.setOnAction(e -> {
				dispatcher.renderView("biglietti", param.getValue(), utente);
			});
			return new SimpleObjectProperty<Button>(bigliettiButton);
		});
		recensioneTableColumn.setCellValueFactory((CellDataFeatures<Concerto, Button> param) -> {
			final Button recensioneButton = new Button("Recensione");
			recensioneButton.setOnAction(e -> {
				try {
					dispatcher.openNewWindow("recensione", param.getValue(), utente);
				} catch (ViewException ex) {
					ex.printStackTrace();
				}
			});
			return new SimpleObjectProperty<Button>(recensioneButton);
		});
		try {
			List<Concerto> concerti = new ArrayList<>();
			for (Concerto concert : concertoService.findAllConcerti()) {
				for (Biglietto ticket : bigliettoService.findAllBiglietti(utente)) {
					if (concert.getId() == ticket.getConcerto().getId()) {
						concerti.add(concert);
						break;
					}
				}
				continue;
			}
			ObservableList<Concerto> concertiData = FXCollections.observableArrayList(concerti);
			concertiTableView.setItems(concertiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}
