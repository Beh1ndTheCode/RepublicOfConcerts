package it.univaq.disim.oop.roc.controller.finestre.spettatore;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RecensioneController implements DataInitializable<Concerto>, UtenteInitializable<Utente> {

	@FXML
	private Button salvaButton, eliminaButton;

	@FXML
	private TextField votoTextField, titoloTextField;

	@FXML
	private TextArea recensioneTextArea;

	@FXML
	private Label approvatoLabel;

	private ViewDispatcher dispatcher;

	private RecensioniService recensioniService;

	private Recensione recensione;

	private Concerto concerto;

	private Utente utente;

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

	@Override
	public void initializeData(Concerto concerto) {
		this.concerto = concerto;
	}

				//cerca se esiste una recensione di questo utente per questo concerto
	@Override	//se esiste inizializza tutti i Field, fa apparire il pulsante Elimina e la Label approvato
	public void initializeUtente(Utente utente) {
		this.utente = utente;
		try {
			for (Recensione review : recensioniService.findRecensioniByUtente(utente)) {
				if (review.getConcerto().getId() == concerto.getId())
					recensione = review;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if (recensione != null) {
			voto = recensione.getValutazione();
			eliminaButton.setVisible(true);
			votoTextField.setPromptText(voto.toString());
			titoloTextField.setPromptText(recensione.getTitolo());
			recensioneTextArea.setPromptText(recensione.getDescrizione().replace("_", " "));
			if (recensione.getApprovato())
				approvatoLabel.setText("Approvato: Si");
			else
				approvatoLabel.setText("Approvato: No");
		}
	}

	@FXML	//aumenta il voto e verifica che non superi 5
	public void piuAction(ActionEvent event) {
		if (voto < 5) {
			voto++;
			votoModificato = true;
		}
		votoTextField.setText(voto.toString());

	}

	@FXML	//diminuisce il voto e verifica che non scenda sotto lo 0
	public void menoAction(ActionEvent event) {
		if (voto > 0) {
			voto--;
			votoModificato = true;
		}
		votoTextField.setText(voto.toString());
	}

			//se non c'è una recensione necessita che ogni campo sia riempito per sbloccare il bottone
	@FXML	//altrimenti basta che ne venga modificato uno
	public void blockSalvaButton() {
		if (recensione == null) {
			String inputTitolo = titoloTextField.getText();
			String inputRecensione = recensioneTextArea.getText();

			if (votoModificato && !(inputTitolo.isEmpty() || inputRecensione.isEmpty()))
				salvaButton.setDisable(false);
		} else {
			String inputTitolo = titoloTextField.getPromptText();
			String inputRecensione = recensioneTextArea.getPromptText();

			if (votoModificato || !inputTitolo.equals(recensione.getTitolo())
					|| inputRecensione.equals(recensione.getDescrizione()))
				salvaButton.setDisable(false);
		}
	}

	@FXML	//si attiva cliccando su Salva, salva i dati della recensione e imposta l'approvazione su False
			//se la recenisone non esisteva la crea, se esisteva fa l'update, infine chiude la finestra
	public void salvaButtonAction(ActionEvent event) {
		if (recensione == null) {
			recensione = new Recensione();
			recensione.setUtente(utente);
			recensione.setConcerto(concerto);
			recensione.setTitolo(titoloTextField.getText());
			String descrizione = recensioneTextArea.getText().replace("\n", "_");
			recensione.setDescrizione(descrizione);
			recensione.setValutazione(voto);
			recensione.setApprovato(false);
			try {
				recensioniService.addRecensione(recensione);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			dispatcher.closeWindowView();
			dispatcher.renderView("ituoiconcerti", utente);
		} else {
			if (!titoloTextField.getText().isEmpty())
				recensione.setTitolo(titoloTextField.getText());
			if (!recensioneTextArea.getText().isEmpty())
				recensione.setDescrizione(recensioneTextArea.getText().replace("\n", "_"));
			recensione.setValutazione(voto);
			recensione.setApprovato(false);
			try {
				recensioniService.updateRecensione(recensione);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			dispatcher.closeWindowView();
			dispatcher.renderView("ituoiconcerti", utente);
		}
	}

	@FXML	//si attiva con il pulsante Elimina, elimina la recensione e chiude la finestra
	public void deleteAction(ActionEvent event) {
		try {
			recensioniService.deleteRecensione(recensione);
			dispatcher.closeWindowView();
			dispatcher.renderView("ituoiconcerti", utente);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}

}
