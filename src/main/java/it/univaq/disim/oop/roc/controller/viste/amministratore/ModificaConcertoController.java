package it.univaq.disim.oop.roc.controller.viste.amministratore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.Utility;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.TipologiaMetodoDiPagamento;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ModificaConcertoController implements DataInitializable<Concerto> {

	@FXML
	private TextField giornoTextField, meseTextField, annoTextField, artistiTextField, scalettaTextField;

	@FXML
	private RadioButton cartaRadioButton, contoRadioButton;

	@FXML
	private Label luogoLabel, dataErrorLabel, tourLabel;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private ConcertoService concertoService;

	private Concerto concerto;

	public ModificaConcertoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		concertoService = factory.getConcertoService();
	}

	public void initialize() {

	}

	@Override
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;

		if (concerto.getTipoMetodo() == TipologiaMetodoDiPagamento.Carta)
			cartaRadioButton.setSelected(true);

		if (concerto.getTipoMetodo() == TipologiaMetodoDiPagamento.Conto)
			contoRadioButton.setSelected(true);

		if (!(concerto.getScaletta() == null))
			scalettaTextField.setPromptText(concerto.getScaletta());

		if (!(concerto.getTour() == null))
			tourLabel.setText(concerto.getTour().getNome());
		else
			tourLabel.setText("Nessuno");

		artistiTextField.setPromptText(concerto.getArtista());
		giornoTextField.setPromptText(concerto.getData().format(DateTimeFormatter.ofPattern("dd")));
		meseTextField.setPromptText(concerto.getData().format(DateTimeFormatter.ofPattern("MM")));
		annoTextField.setPromptText(concerto.getData().format(DateTimeFormatter.ofPattern("yyyy")));
		luogoLabel.setText(concerto.getLuogo().toString());
	}

	@FXML
	public void setContoAction(ActionEvent event) {
		concerto.setTipoMetodo(TipologiaMetodoDiPagamento.Conto);
	}

	@FXML
	public void setCartaAction(ActionEvent event) {
		concerto.setTipoMetodo(TipologiaMetodoDiPagamento.Carta);
	}

	@FXML
	public void updateConcertoAction(ActionEvent event) throws BusinessException {
		try {
			if (giornoTextField.getText().isEmpty() || meseTextField.getText().isEmpty()
					|| annoTextField.getText().isEmpty()) {
				if (!(giornoTextField.getText().isEmpty() && meseTextField.getText().isEmpty()
						&& annoTextField.getText().isEmpty()))
					throw new InvalidDateException();
			} else {
				LocalDate data = Utility.VerificaData(giornoTextField.getText(), meseTextField.getText(),
						annoTextField.getText());
				concerto.setData(data);
			}
			if (!scalettaTextField.getText().isEmpty())
				concerto.setScaletta(scalettaTextField.getText());
			if (!artistiTextField.getText().isEmpty())
				concerto.setArtista(artistiTextField.getText());
			concertoService.updateConcerto(concerto);

			dispatcher.renderView("gestioneconcerti");
		} catch (IntegerFormatException e) {
			dataErrorLabel.setText("Data non valida");
		} catch (InvalidDateException e) {
			dataErrorLabel.setText("Data non valida");
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
