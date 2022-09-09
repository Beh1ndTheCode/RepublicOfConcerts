package it.univaq.disim.oop.roc.controller.finestre.amministratore;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ModificaRecensioneController implements DataInitializable<Recensione> {

	@FXML
	private Label votoLabel, titoloLabel;

	@FXML
	private Text recensioneText;

	@FXML
	private Button approvaButton, eliminaButton;

	private ViewDispatcher dispatcher;

	private RecensioniService recensioniService;

	private Recensione recensione;

	public ModificaRecensioneController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		recensioniService = factory.getRecensioniService();
	}

	@Override
	public void initializeData(Recensione recensione) {
		this.recensione = recensione;
		votoLabel.setText(recensione.getValutazione().toString() + "/5");
		titoloLabel.setText(recensione.getTitolo());
		recensioneText.setText(recensione.getDescrizione().replace("\n", " "));
	}

	@FXML
	public void approvaButtonAction(ActionEvent event) {
		recensione.setApprovato(true);
		try {
			recensioniService.updateRecensione(recensione);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
		dispatcher.closeWindowView();
		dispatcher.renderView("gestionerecensioni");
	}

	@FXML
	public void eliminaButtonAction(ActionEvent event) {
		try {
			recensioniService.deleteRecensione(recensione);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
		dispatcher.closeWindowView();
		dispatcher.renderView("gestionerecensioni");
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
