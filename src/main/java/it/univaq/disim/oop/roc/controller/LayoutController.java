package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LayoutController implements DataInitializable<Utente> {
	
	@FXML
	private BorderPane layout;

	@FXML
	private Pane barraSuperiore;

	@FXML
	private MenuButton bottoneMenu;

	@FXML
	private MenuItem bottoneConcerti, bottoneTour, bottoneRecensioni, bottoneStrutture;

	@FXML
	private ImageView iconaDiRicerca, bottoneProfilo, bottoneUscita;

	@FXML
	private TextField barraDiRicerca;

	@FXML
	private Text titoloPagina;

	
	public LayoutController() {
		ViewDispatcher.getInstance();
	}
	
	public void initializeData(Utente utente){
		if(utente instanceof Amministratore) {
			bottoneMenu.setText("gestisci");
			bottoneMenu.getItems().add(new MenuItem("concerti"));
			bottoneMenu.getItems().add(new MenuItem("tour"));
			bottoneMenu.getItems().add(new MenuItem("artisti"));
			bottoneMenu.getItems().add(new MenuItem("recensioni"));
		}
		if(utente instanceof Spettatore) {
			bottoneMenu.setText("menu");
			bottoneMenu.getItems().add(new MenuItem("concerti"));
			bottoneMenu.getItems().add(new MenuItem("tour"));
			bottoneMenu.getItems().add(new MenuItem("artisti"));
		}
	}
}
