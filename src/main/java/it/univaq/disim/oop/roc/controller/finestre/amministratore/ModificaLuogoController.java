package it.univaq.disim.oop.roc.controller.finestre.amministratore;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ModificaLuogoController implements DataInitializable<Luogo> {

	@FXML
	private Text nomeText, cittaText, capienzaText;

	@FXML
	private Label nomeLabel, cittaLabel, capienzaLabel, capienzaErrorLabel;

	@FXML
	private TextField nomeTextField, cittaTextField, capienzaTextField;

	@FXML
	private Button modificaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private LuogoService luogoService;

	private Luogo luogo;

	public ModificaLuogoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		luogoService = factory.getLuogoService();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}
	
	//inizializza tutte le Label in base al luogo dato
	public void initializeData(Luogo luogo) {
		this.luogo = luogo;
		nomeLabel.setText(luogo.getNome());
		nomeTextField.setPromptText(luogo.getNome());
		cittaLabel.setText(luogo.getCitta());
		cittaTextField.setPromptText(luogo.getCitta());
		capienzaLabel.setText((Integer.toString(luogo.getCapienza())));
		capienzaTextField.setPromptText((Integer.toString(luogo.getCapienza())));
	}

	public void blockModificaButton() {
		String nome = nomeTextField.getText();
		String citta = cittaTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() && citta.isEmpty() && capienza.isEmpty();
		modificaButton.setDisable(isDisable);
	}

	// verifica che la capienza sia inserita correttamente e aggiorna il Luogo
	// la capienza non può essere minore della somma della capienza dei settori del Luogo
	public void updateLuogoAction(ActionEvent event) {
		capienzaErrorLabel.setText("");
		try {
			if (capienzaTextField.getText().length() > 0) {
				Integer capienzaInput;
				Integer capienzaRimanente;
				try {
					capienzaInput = Integer.parseInt(capienzaTextField.getText());
					capienzaRimanente = luogoService.getCapienzaRimanente(luogo);
						if(capienzaInput < luogo.getCapienza()-capienzaRimanente)
							throw new NumberOutOfBoundsException();
						luogo.setCapienza(capienzaInput);
				} catch (NumberOutOfBoundsException e) {
					capienzaErrorLabel.setText("capienza troppo bassa per i settori inseriti");
				} catch (NumberFormatException e) {
					capienzaErrorLabel.setText("Capienza non valida!");
				}
			}

			if (!nomeTextField.getText().isEmpty())
				luogo.setNome(nomeTextField.getText());
			if (!cittaTextField.getText().isEmpty())
				luogo.setCitta(cittaTextField.getText());
			luogoService.updateLuogo(luogo);

			initializeData(luogo);
			nomeTextField.setText("");
			cittaTextField.setText("");
			capienzaTextField.setText("");
			blockModificaButton();
			dispatcher.renderView("gestioneluoghi");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void deleteLuogoAction(ActionEvent event) {
		try {
			luogoService.deleteLuogo(luogo);
			dispatcher.closeWindowView();
			dispatcher.renderView("gestioneluoghi");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
