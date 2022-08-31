package it.univaq.disim.oop.roc.controller.viste;

import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMConcertoServiceImpl;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModificaConcertoController implements DataInitializable<Object> {

	@FXML
	private TextArea artistiTextArea, scalettaTextArea;

	@FXML
	private TextField giornoTextField, meseTextField, annoTextField, tourTextField;

	@FXML
	private RadioButton cartaRadioButton, contoRadioButton;

	@FXML
	private Label luogoLabel, luogoErrorLabel;

	@FXML
	private ListView<Luogo> luoghiListView;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private LuogoService luoghiService;

	private Concerto concerto;

	public ModificaConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		try {
			List<Luogo> luoghi = luoghiService.findAllLuoghi();
			ObservableList<Luogo> luoghiData = FXCollections.observableArrayList(luoghi);
			luoghiListView.setItems(luoghiData);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void initializeData(Concerto concerto) {
		this.concerto = concerto;

		if (!(concerto.getArtista() == null))
			artistiTextArea.setPromptText(concerto.getArtista());
		if (!(concerto.getScaletta() == null))
			artistiTextArea.setPromptText(concerto.getScaletta());
		if (!(concerto.getData() == null)) {
			giornoTextField.setPromptText(((Integer) concerto.getData().getDayOfMonth()).toString());
			meseTextField.setPromptText(((Integer) concerto.getData().getMonthValue()).toString());
			annoTextField.setPromptText(((Integer) concerto.getData().getYear()).toString());
		}
		if (!(concerto.getScaletta() == null))
			tourTextField.setPromptText(concerto.getTour().toString());

	}

	public void luogoSelezionato() {
		try {
			if (luoghiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			luogoErrorLabel.setText(null);
			luogoLabel.setText(luoghiListView.getSelectionModel().getSelectedItem().toString());
		} catch (SelectionException e) {
			luogoErrorLabel.setText("Seleziona un luogo");
		}
	}

	public void updateConcertoAction(ActionEvent event) {

	}

	public void deleteConcertoAction(ActionEvent event) {
		try {
			concertoService.deleteConcerto(concerto);
			dispatcher.renderView("gestioneconcerti");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void setCartaAction(ActionEvent event) {

	}

	public void setContoAction(ActionEvent event) {

	}
}
