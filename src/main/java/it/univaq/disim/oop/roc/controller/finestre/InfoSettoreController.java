package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBounds;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InfoSettoreController implements DataInitializable<Settore> {

	@FXML
	private Text nomeText, capienzaText;

	@FXML
	private Label nomeLabel, capienzaLabel, errorLabel;

	@FXML
	private TextField nomeTextField, capienzaTextField;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private LuogoService luogoService;

	private Settore settore;

	public InfoSettoreController() {
		dispatcher = ViewDispatcher.getInstance();
		luogoService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}

	public void initializeData(Settore settore) {
		this.settore = settore;
		nomeLabel.setText(settore.getNome());
		nomeTextField.setPromptText(settore.getNome());
		capienzaLabel.setText((Integer.toString(settore.getCapienza())));
		capienzaTextField.setPromptText((Integer.toString(settore.getCapienza())));
	}

	public void blockModificaButton() {
		String nome = nomeTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() && capienza.isEmpty();
		modificaButton.setDisable(isDisable);
	}

	public void updateSettoreAction(ActionEvent event) {
		try {
			errorLabel.setText("");
			luogoService.updateSettore(settore, nomeTextField.getText(), capienzaTextField.getText());
			initializeData(settore);
			nomeTextField.setText("");
			capienzaTextField.setText("");
			blockModificaButton();
			dispatcher.renderView("gestionesettori", settore.getLuogo());
		} catch (NumberOutOfBounds e) {
			errorLabel.setText("ridurre la capienza");
		} catch (FloatFormatException e) {
			errorLabel.setText("tariffa non valida!");
		} catch (IntegerFormatException e) {
			errorLabel.setText("capienza non valida!");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void deleteSettoreAction(ActionEvent event) {
		try {
			luogoService.deleteSettore(settore);
			dispatcher.renderView("gestionesettori", settore.getLuogo());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
