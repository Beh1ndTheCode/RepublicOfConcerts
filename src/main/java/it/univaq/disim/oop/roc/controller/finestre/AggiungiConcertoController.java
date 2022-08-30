package it.univaq.disim.oop.roc.controller.finestre;

import java.util.ArrayList;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AggiungiConcertoController implements DataInitializable<Concerto> {

	@FXML
	private TextField artistaTextField, giornoTextField, meseTextField, annoTextField;

	@FXML
	private ListView<String> luoghiListView;

	@FXML
	private Label luogoLabel;

	@FXML
	private Button aggiungiConcertoButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private LuogoService luoghiService;

	public AggiungiConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		try {
			List<Luogo> listLuoghi = luoghiService.findAllLuoghi();
			List<String> luoghi = new ArrayList<>();
			for (Luogo luogo : listLuoghi) {
				luoghi.add(luogo.getId() + ", " + luogo.getNome() + ", " + luogo.getCitta());
			}
			luoghiListView.getItems().addAll(luoghi);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void luogoSelezionato() {
		luogoLabel.setText(luoghiListView.getSelectionModel().getSelectedItem());
	}

	public void blockAggiungiButton() {
		String artista = artistaTextField.getText();
		String giorno = giornoTextField.getText();
		String mese = meseTextField.getText();
		String anno = annoTextField.getText();

		boolean isDisable = artista.isEmpty() || giorno.isEmpty() || mese.isEmpty() || anno.isEmpty();
		aggiungiConcertoButton.setDisable(isDisable);
	}

	public void addConcertoAction(ActionEvent event) {
		// try {
		String data = (giornoTextField.getText() + "/" + meseTextField.getText() + "/" + annoTextField.getText());

		concertoService.addConcerto(artistaTextField.getText(), luogoLabel.getText(), data);

		artistaTextField.setText("");
		giornoTextField.setText("");
		meseTextField.setText("");
		annoTextField.setText("");
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
