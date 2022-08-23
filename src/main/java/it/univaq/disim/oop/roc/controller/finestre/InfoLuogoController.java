package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.impl.ram.RAMLuogoServiceImpl;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InfoLuogoController implements DataInitializable<Luogo> {
	@FXML
	private Text nomeText, cittaText, capienzaText;
	
	@FXML
	private Label nomeLabel, cittaLabel, capienzaLabel, capienzaErrorLabel;
	
	@FXML
	private TextField nomeTextField, cittaTextField, capienzaTextField;
	
	@FXML
	private Button modificaButton;

	private ViewDispatcher dispatcher;
	
	private LuogoService luoghiService;
	
	private Luogo luogo;

	public InfoLuogoController() {
		dispatcher = ViewDispatcher.getInstance();
		luoghiService = new RAMLuogoServiceImpl();
	}

	public void initialize() {
		modificaButton.setDisable(true);
	}

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
		boolean isDisable = nome.isEmpty() || citta.isEmpty() || capienza.isEmpty();
		modificaButton.setDisable(isDisable);
	}
	
	public void updateDatiAction(ActionEvent event){
		capienzaErrorLabel.setText("");
		try {
		Integer capienza;
		if (!capienzaTextField.getText().equals("")) {
			try {
				capienza = Integer.parseInt(capienzaTextField.getText());
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
		}
		else
			capienza = luogo.getCapienza();
		luoghiService.updateDati(luogo, nomeTextField.getText(), cittaTextField.getText(), capienza);
		initializeData(luogo);
		nomeTextField.setText("");
		cittaTextField.setText("");
		capienzaTextField.setText("");
		dispatcher.renderView("gestioneluoghi");
	} catch (IntegerFormatException e) {
		capienzaErrorLabel.setText("capienza non valida!");
	}
}
	
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
