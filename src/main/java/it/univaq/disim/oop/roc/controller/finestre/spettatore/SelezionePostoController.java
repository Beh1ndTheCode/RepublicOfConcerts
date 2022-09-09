package it.univaq.disim.oop.roc.controller.finestre.spettatore;

import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SelezionePostoController implements DataInitializable<Biglietto>, UtenteInitializable<List<Biglietto>> {

	@FXML
	private Label bigliettoLabel, postiLabel, errorLabel;

	@FXML
	private TextField postoTextField;

	@FXML
	private Button salvaButton;

	private Biglietto biglietto;

	private ViewDispatcher dispatcher;

	private BigliettoService bigliettoService;

	private List<Biglietto> bigliettiAggiunti;

	public SelezionePostoController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		bigliettoService = factory.getBigliettoService();
	}

	@Override	//inizializza tutta la lista di biglietti che sono stati creati nella finestra di checkout
	public void initializeUtente(List<Biglietto> bigliettiAggiunti) {
		this.bigliettiAggiunti = bigliettiAggiunti;
	}

	@Override	//inizializza le Label e TextField in base al Biglietto
	public void initializeData(Biglietto biglietto) {
		this.biglietto = biglietto;
		bigliettoLabel.setText(
				"Biglietto: " + biglietto.getTipologiaBiglietto().toString() + ",     Posto: " + biglietto.getPosto());
		postiLabel.setText("min 0   max " + biglietto.getSettore().getCapienza());
		postoTextField.setPromptText(biglietto.getPosto().toString());
	}

			//verifica se viene inserito un valore valido Es.(non sfora la capienza, non è occupato, o se non è un valore numerico)
	@FXML	//se non viene inserito nessun valore si mantiene il posto generato automaticamente, altrimenti si modifica il posto e si fa l'update
			//dopo di che verifica se ci sono altri biglietti, se si chiude la finestra e ne riapre un'altra con il biglietto successivo
	public void salvaPostoAction(ActionEvent event) {
		Integer posto;
		try {
			if (!postoTextField.getText().isEmpty()) {
				posto = Integer.parseInt(postoTextField.getText());
				if (posto > biglietto.getSettore().getCapienza())
					throw new NumberFormatException();
				for (Biglietto tiket : bigliettoService.findBigliettiByConcertoAndSettore(biglietto.getConcerto(),
						biglietto.getSettore())) {
					if (posto == tiket.getPosto())
						throw new BusinessException();
				}
				biglietto.setPosto(posto);
				bigliettoService.updateBiglietto(biglietto);
			}
			dispatcher.closeWindowView();
			if (bigliettiAggiunti != null) {
				for (Biglietto tiket : bigliettiAggiunti) {
					if (tiket.getNumeroBiglietto() == biglietto.getNumeroBiglietto() + 1)
						dispatcher.openNewWindow("selezioneposto", tiket, bigliettiAggiunti);
				}
			}
			else
				dispatcher.renderView("biglietti", biglietto.getConcerto(), biglietto.getUtente());
		} catch (NumberFormatException e) {
			errorLabel.setText("Inserisci un posto valido");
		} catch (BusinessException e) {
			errorLabel.setText("Posto già occupato");
		} catch (ViewException e) {
			e.printStackTrace();
		}
	}
}
