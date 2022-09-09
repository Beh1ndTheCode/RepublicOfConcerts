package it.univaq.disim.oop.roc.controller.finestre.amministratore;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AggiungiSettoreController implements DataInitializable<Luogo> {

	@FXML
	private Label capienzaErrorLabel;

	@FXML
	private TextField nomeTextField, capienzaTextField;

	@FXML
	private Button aggiungiSettoreButton;

	private ViewDispatcher dispatcher;

	private LuogoService luoghiService;

	private Luogo luogo;

	public AggiungiSettoreController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		luoghiService = factory.getLuogoService();
	}

	public void initialize() {
		aggiungiSettoreButton.setDisable(true);
	}

	public void initializeData(Luogo luogo) {
		this.luogo = luogo;
	}

	public void blockAggiungiButton() {
		String nome = nomeTextField.getText();
		String capienza = capienzaTextField.getText();
		boolean isDisable = nome.isEmpty() || capienza.isEmpty();
		aggiungiSettoreButton.setDisable(isDisable);
	}
	
	//si attiva con il pulsante Aggiungi e prima verifica che la capienza sia un numero
	//poi che non sfori la capienza del Luogo e poi viene creato il settore 
	//impostando la capienza al 70% se si tratta di un Teatro
	public void addSettoreAction(ActionEvent event) {
		capienzaErrorLabel.setText("");
		try {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienzaTextField.getText());
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
			Integer capienzaRimanente = luoghiService.getCapienzaRimanente(luogo);
			capienzaRimanente -= capienzaInput;
			if (capienzaRimanente < 0)
				throw new NumberOutOfBoundsException();

			Settore settore = new Settore();
			if (luogo.getTipologiaLuogo().toString().equals("Teatro")) {
				settore.setNome(nomeTextField.getText());
				capienzaInput -= ((capienzaInput * 3) / 10);
				settore.setCapienza(capienzaInput);
				settore.setLuogo(luogo);
				luogo.getSettori().add(settore);
			}

			if (luogo.getTipologiaLuogo().toString().equals("Stadio")) {
				settore.setNome(nomeTextField.getText());
				settore.setCapienza(capienzaInput);
				settore.setLuogo(luogo);
				luogo.getSettori().add(settore);
			}
			luoghiService.addSettore(settore);

			nomeTextField.setText("");
			capienzaTextField.setText("");
			blockAggiungiButton();
			dispatcher.renderView("gestionesettori", luogo);
		} catch (NumberOutOfBoundsException e) {
			capienzaErrorLabel.setText("ridurre la capienza");
		} catch (NumberFormatException e) {
			capienzaErrorLabel.setText("capienza non valida");
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}

}
