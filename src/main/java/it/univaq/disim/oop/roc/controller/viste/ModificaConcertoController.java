package it.univaq.disim.oop.roc.controller.viste;

import java.time.LocalDate;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.Utility;
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
	private TextArea scalettaTextArea;

	@FXML
	private TextField giornoTextField, meseTextField, annoTextField, artistiTextField;

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

	private Luogo luogo;

	public ModificaConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		concertoService = new RAMConcertoServiceImpl();
		luoghiService = new RAMLuogoServiceImpl();
		// concertoService = new FileConcertoServiceImpl();
		// luoghiService = new FileLuogoServiceImpl();
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

	@Override
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;

		if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Carta)
			cartaRadioButton.setSelected(true);

		if (concerto.getTipoMetodo() == TipoMetodoDiPagamento.Conto)
			contoRadioButton.setSelected(true);

		if (!(concerto.getScaletta() == null))
			scalettaTextArea.setPromptText(concerto.getScaletta());

		if (!(concerto.getTour() == null))
			tourLabel.setText(concerto.getTour().toString());
		else
			tourLabel.setText("nessuno");

		artistiTextField.setPromptText(concerto.getArtista());
		giornoTextField.setPromptText(((Integer) concerto.getData().getDayOfMonth()).toString());
		meseTextField.setPromptText(((Integer) concerto.getData().getMonthValue()).toString());
		annoTextField.setPromptText(((Integer) concerto.getData().getYear()).toString());
		luogoLabel.setText(concerto.getLuogo().toString());

	}

	@FXML
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

	@FXML
	public void setContoAction(ActionEvent event) {
		concerto.setTipoMetodo(TipoMetodoDiPagamento.Conto);
	}

	@FXML
	public void setCartaAction(ActionEvent event) {
		concerto.setTipoMetodo(TipoMetodoDiPagamento.Carta);
	}

	@FXML
	public void updateConcertoAction(ActionEvent event) throws BusinessException {
		try {
			if (luoghiListView.getSelectionModel().getSelectedItem() == null)
				luogo = concerto.getLuogo();
			else
				luogo = luoghiListView.getSelectionModel().getSelectedItem();

			if (giornoTextField.getText().isEmpty() || meseTextField.getText().isEmpty()
					|| annoTextField.getText().isEmpty()) {
				if (!(giornoTextField.getText().isEmpty() && meseTextField.getText().isEmpty()
						&& annoTextField.getText().isEmpty()))
					throw new InvalidDateException();
			} else {
				try {
					LocalDate data = Utility.VerificaData(giornoTextField.getText(), meseTextField.getText(),
							annoTextField.getText());
					concerto.setData(data);
				} catch (IntegerFormatException e) {
					throw new IntegerFormatException();
				} catch (InvalidDateException e) {
					throw new InvalidDateException();
				}
			}

			if (!scalettaTextArea.getText().isEmpty())
				concerto.setScaletta(scalettaTextArea.getText());
			if (!artistiTextField.getText().isEmpty())
				concerto.setArtista(artistiTextField.getText());
			concerto.setLuogo(luogo);
			concertoService.updateConcerto(concerto);

			dispatcher.renderView("gestioneconcerti");
		} catch (IntegerFormatException e) {
			dataErrorLabel.setText("data non valida");
		} catch (InvalidDateException e) {
			dataErrorLabel.setText("data non valida");
		}
	}

	@FXML
	public void deleteConcertoAction(ActionEvent event) {
		try {
			concertoService.deleteConcerto(concerto);
			dispatcher.renderView("gestioneconcerti");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
}
