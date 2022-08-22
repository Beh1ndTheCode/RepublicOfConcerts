package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoLuogoController implements DataInitializable<Luogo> {
	@FXML
	private Text nomeText, cittaText, capienzaText;

	@FXML
	private Label nomeLabel, cittaLabel, capienzaLabel;

	private ViewDispatcher dispatcher;

	public InfoLuogoController() {
		dispatcher = ViewDispatcher.getInstance();
	}

	public void initialize() {

	}

	public void initializeData(Luogo luogo) {
		nomeText.setVisible(true);
		cittaText.setVisible(true);
		capienzaText.setVisible(true);
		nomeLabel.setText(luogo.getNome());
		cittaLabel.setText(luogo.getCitta());
		capienzaLabel.setText((Integer.toString(luogo.getCapienza())));
	}

	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
