package it.univaq.disim.oop.roc.controller.viste;

import it.univaq.disim.oop.roc.controller.DataInitializable;
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
	private ImageView iconaDiRicerca, bottoneProfilo, bottoneUscita;

	@FXML
	private TextField barraDiRicerca;

	@FXML
	private Text titoloPagina;

	private ViewDispatcher dispatcher;

	private Utente utente;

	private static final MenuItem[] MENU_AMMINISTRATORE = { new MenuItem("concerti"), new MenuItem("tour"),
			new MenuItem("luoghi"), new MenuItem("recensioni") };
	private static final MenuItem[] MENU_SPETTATORE = { new MenuItem("concerti"), new MenuItem("tour") };

	public LayoutController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initializeData(Utente utente) {
		this.utente = utente;
		if (utente instanceof Amministratore) {
			bottoneMenu.setText("Gestisci");
			for (MenuItem menu : MENU_AMMINISTRATORE) {
				bottoneMenu.getItems().add(menu);
				menu.setOnAction(e -> {
					dispatcher.renderView("gestione" + menu.getText());
					titoloPagina.setText(menu.getText().toUpperCase());
				});
			}
			bottoneProfilo.setVisible(false);
		}
		if (utente instanceof Spettatore) {
			bottoneMenu.setText("Menù");
			for (MenuItem menu : MENU_SPETTATORE) {
				bottoneMenu.getItems().add(menu);
				menu.setOnAction(e -> {
					dispatcher.renderView(menu.getText(), utente);
					titoloPagina.setText(menu.getText());
				});
			}
		}
	}

	public void exitToLoginVIew() {
		dispatcher.logout();
	}

	public void goToProfiloView() throws Exception {
		dispatcher.renderView("profilo", utente);
		titoloPagina.setText("profilo".toUpperCase());
	}
}
