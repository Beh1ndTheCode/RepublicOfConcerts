package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecensioneController implements DataInitializable<Concerto>, UtenteInitializable<Utente> {
	
	@FXML
	private Button salvaButton, eliminaButton;
	
	@FXML
	private TextField votoTextField, titoloTextField, recensioneTextArea;
	
	private ViewDispatcher dispatcher;
	
	private RocBusinessFactory businessFactory;
	
	private RecensioniService recensioniService;
	
	private Recensione recensione;
	
	private Concerto concerto;
	
	private Spettatore spettatore;
	
	private Integer voto;
	
	private Boolean votoModificato;
	
	public RecensioneController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		recensioniService = factory.getRecensioniService();
	}
	
	public void initialize() {
		votoModificato = false;
		voto = 0;
		eliminaButton.setVisible(false);
		salvaButton.setDisable(true);
	}
	
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;
	}
	
	public void initializeUtente(Utente utente) {
		this.spettatore = (Spettatore) utente;
		for (Recensione rec : spettatore.getRecensioniLasciate()) {
			if (rec.getConcerto() == concerto)
				recensione = rec;
		}
		if (recensione != null) {
			voto = recensione.getValutazione();
			eliminaButton.setVisible(true);
			votoTextField.setPromptText(voto.toString());
			titoloTextField.setPromptText(recensione.getTitolo());
			recensioneTextArea.setPromptText(recensione.getDescrizione());
		}
	}
	
	@FXML
	public void piuAction(ActionEvent event) {
		if (voto < 5) {
			voto++;
			votoModificato = true;
		}
		votoTextField.setText(voto.toString());
		
	}

	@FXML
	public void menoAction(ActionEvent event) {
		if (voto > 0) {
			voto--;
			votoModificato = true;
		}
		votoTextField.setText(voto.toString());
	}
	
	public void blockSalvaButton() {
		if (recensione == null) {
			String inputTitolo = titoloTextField.getText();
			String inputRecensione = recensioneTextArea.getText();
			
			if (votoModificato  && !(inputTitolo.isEmpty() || inputRecensione.isEmpty()))
					salvaButton.setDisable(false);
		}
		else {
			String inputTitolo = titoloTextField.getPromptText();
			String inputRecensione = recensioneTextArea.getPromptText();
			
			if (votoModificato  || !inputTitolo.equals(recensione.getTitolo()) || inputRecensione.equals(recensione.getDescrizione()))
				salvaButton.setDisable(false);
		}
	}
	
	public void salvaButtonAction(ActionEvent event) {
		if (recensione == null) {
			recensione.setUtente(spettatore);
			recensione.setConcerto(concerto);
			recensione.setTitolo(titoloTextField.getText());
			recensione.setDescrizione(recensioneTextArea.getText());
			recensione.setValutazione(voto);
			recensione.setApprovato(false);
			try {
				recensioniService.addRecensione(recensione);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			closeWindow();
			try {
				dispatcher.openNewWindow("recensione", concerto, spettatore);
			} catch (ViewException e) {
				e.printStackTrace();
			}
		}
		else {
			if (!titoloTextField.getText().isEmpty())
				recensione.setTitolo(titoloTextField.getText());
			if (!recensioneTextArea.getText().isEmpty())
				recensione.setDescrizione(recensioneTextArea.getText());
			recensione.setValutazione(voto);
			try {
				recensioniService.updateRecensione(recensione);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			closeWindow();
			try {
				dispatcher.openNewWindow("recensione", concerto, spettatore);
			} catch (ViewException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAction(ActionEvent event) {
		try {
			recensioniService.deleteRecensione(recensione);
			closeWindow();
			dispatcher.renderView("ituoiconcerti", spettatore);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}
	
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
	
	
}
