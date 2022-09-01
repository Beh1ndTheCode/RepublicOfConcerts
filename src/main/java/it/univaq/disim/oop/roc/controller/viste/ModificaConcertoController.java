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
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;
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

public class ModificaConcertoController implements DataInitializable<Concerto> {

	@FXML
	private TextArea artistiTextArea, scalettaTextArea;

	@FXML
	private TextField giornoTextField, meseTextField, annoTextField;

	@FXML
	private RadioButton cartaRadioButton, contoRadioButton;

	@FXML
	private Label luogoLabel, dataErrorLabel, luogoErrorLabel, tourLabel;

	@FXML
	private ListView<Luogo> luoghiListView;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private LuogoService luoghiService;

	private Concerto concerto;

	private TipoMetodoDiPagamento tipoMetodo;

	public ModificaConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		this.tipoMetodo = TipoMetodoDiPagamento.Carta;
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

		tipoMetodo = concerto.getMetodo();
		if (tipoMetodo == TipoMetodoDiPagamento.Carta)
			cartaRadioButton.setSelected(true);

		if (tipoMetodo == TipoMetodoDiPagamento.Conto)
			contoRadioButton.setSelected(true);

		if (!(concerto.getScaletta() == null))
			scalettaTextArea.setPromptText(concerto.getScaletta());

		if (!(concerto.getTour() == null))
			tourLabel.setText(concerto.getTour().toString());

		else
			tourLabel.setText("nessuno");

		artistiTextArea.setPromptText(concerto.getArtista());
		giornoTextField.setPromptText(((Integer) concerto.getData().getDayOfMonth()).toString());
		meseTextField.setPromptText(((Integer) concerto.getData().getMonthValue()).toString());
		annoTextField.setPromptText(((Integer) concerto.getData().getYear()).toString());
		luogoLabel.setText(concerto.getLuogo().toString());

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

	public void setContoAction(ActionEvent event) {
		tipoMetodo = TipoMetodoDiPagamento.Conto;
	}

	public void setCartaAction(ActionEvent event) {
		tipoMetodo = TipoMetodoDiPagamento.Carta;
	}

	public void updateConcertoAction(ActionEvent event) throws BusinessException {
		try {
			if (luoghiListView.getSelectionModel().getSelectedItem() == null)
				throw new SelectionException();
			concertoService.updateConcerto(concerto, scalettaTextArea.getText(), artistiTextArea.getText(), tipoMetodo,
					giornoTextField.getText(), meseTextField.getText(), annoTextField.getText(),
					luoghiListView.getSelectionModel().getSelectedItem());
			dispatcher.renderView("gestioneconcerti");
		} catch (IntegerFormatException e) {
			dataErrorLabel.setText("data non valida");
		} catch (InvalidDateException e) {
			dataErrorLabel.setText("data non valida");
		} catch (SelectionException e) {
			luogoErrorLabel.setText("Seleziona un luogo");
		}
	}

	public void deleteConcertoAction() {
		try {
			concertoService.deleteConcerto(concerto);
			dispatcher.renderView("gestioneconcerti");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}
