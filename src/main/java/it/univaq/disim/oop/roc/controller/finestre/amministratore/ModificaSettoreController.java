package it.univaq.disim.oop.roc.controller.finestre.amministratore;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ModificaSettoreController implements DataInitializable<Settore> {

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

	public ModificaSettoreController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		luogoService = factory.getLuogoService();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}

	@Override	//inizializza le Label e i TextField in base al Settore
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

	@FXML	//si attiva cliccando su Modifica, verifica la capienza che, aggiunta a quella degli
			//altri settori non deve superare quella del luogo, modifica la capienza e fa l'update
	public void updateSettoreAction(ActionEvent event) {
		errorLabel.setText("");
		try {
			Integer capienzaInput;
			if (capienzaTextField.getText().length() >= 0 && !capienzaTextField.getText().isEmpty()) {
				try {
					capienzaInput = Integer.parseInt(capienzaTextField.getText());
					Integer capienzaRimanente = luogoService.getCapienzaRimanente(settore.getLuogo());
					capienzaRimanente += settore.getCapienza();
					capienzaRimanente -= capienzaInput;
					if (capienzaRimanente < 0)
						throw new NumberOutOfBoundsException();
					settore.setCapienza(capienzaInput);
				} catch (NumberFormatException e) {
					errorLabel.setText("capienza non valida!");
				} catch (NumberOutOfBoundsException e) {
					errorLabel.setText("ridurre la capienza");
				}
			}

			if (!nomeTextField.getText().isEmpty())
				settore.setNome(nomeTextField.getText());
			luogoService.updateSettore(settore);
			dispatcher.closeWindowView();
			dispatcher.renderView("gestionesettori", settore.getLuogo());

		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	//si attiva cliccando su Elimina, elimina il Settore e chiude la finistra
	public void deleteSettoreAction(ActionEvent event) {
		try {
			luogoService.deleteSettore(settore);
			dispatcher.closeWindowView();
			dispatcher.renderView("gestionesettori", settore.getLuogo());
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
