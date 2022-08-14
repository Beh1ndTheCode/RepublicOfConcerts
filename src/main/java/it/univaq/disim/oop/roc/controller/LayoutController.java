package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
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

	private Utente profilo;

	private static final MenuItem[] MENU_AMMINISTRATORE = { new MenuItem("concerti"), new MenuItem("tour"),
			new MenuItem("artisti"), new MenuItem("recensioni") };
	private static final MenuItem[] MENU_SPETTATORE = { new MenuItem("concerti"), new MenuItem("tour"),
			new MenuItem("artisti") };

	public LayoutController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initializeData(Utente utente) {
		if (utente instanceof Amministratore) {
			bottoneMenu.setText("gestione");
			for (MenuItem menu : MENU_AMMINISTRATORE) {
				bottoneMenu.getItems().add(menu);
				menu.setOnAction(e -> {
					dispatcher.renderView("gestione" + menu.getText(), utente);
					titoloPagina.setText(menu.getText());
				});
			}
			bottoneProfilo.setVisible(false);
		}
		if (utente instanceof Spettatore) {
			bottoneMenu.setText("menù");
			for (MenuItem menu : MENU_SPETTATORE) {
				bottoneMenu.getItems().add(menu);
				menu.setOnAction(e -> {
					dispatcher.renderView(menu.getText(), utente);
					titoloPagina.setText(menu.getText());
				});
			}
		}
	}

	public void exitToLoginVIew() throws Exception {
		try {
			dispatcher.loginView();
		} catch (ViewException e) {
			e.printStackTrace();
		}
	}

	public void goToProfiloView() throws Exception {
		dispatcher.renderView("profilo", profilo);
		titoloPagina.setText("profilo");
	}
}
