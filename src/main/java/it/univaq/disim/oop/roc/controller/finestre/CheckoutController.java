package it.univaq.disim.oop.roc.controller.finestre;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckoutController implements DataInitializable<Tariffa>, UtenteInitializable<MetodoDiPagamento> {

	@FXML
	private Label prezzoLabel, metodoLabel, errorLabel;

	@FXML
	private Button compraButton, piuInteroButton, menoInteroButton, piuRidottoButton, menoRidottoButton;

	@FXML
	private TextField interoTextField, ridottoTextField;

	private ViewDispatcher dispatcher;

	private BigliettoService bigliettoService;

	private MetodoDiPagamento metodo;

	private Tariffa tariffa;

	private Integer numBigliettiRidotti, numBigliettiInteri;

	private String prezzo;

	public CheckoutController() {
		dispatcher = ViewDispatcher.getInstance();
		RocBusinessFactory factory = RocBusinessFactory.getInstance();
		bigliettoService = factory.getBigliettoService();
	}

	public void initialize() {
		compraButton.setDisable(true);
		numBigliettiRidotti = 0;
		numBigliettiInteri = 0;
		prezzo = "0.0€";
		ridottoTextField.setText(numBigliettiRidotti.toString());
		interoTextField.setText(numBigliettiInteri.toString());
		prezzoLabel.setText(prezzo.toString());
	}

	@Override
	public void initializeData(Tariffa tariffa) {
		this.tariffa = tariffa;
	}

	@Override
	public void initializeUtente(MetodoDiPagamento metodo) {
		this.metodo = metodo;
		metodoLabel.setText(metodo.toString());
	}

	@FXML
	public void piuRidottoAction(ActionEvent event) {
		numBigliettiRidotti++;
		ridottoTextField.setText(numBigliettiRidotti.toString());
	}

	@FXML
	public void menoRidottoAction(ActionEvent event) {
		if (numBigliettiRidotti > 0)
			numBigliettiRidotti--;
		ridottoTextField.setText(numBigliettiRidotti.toString());
	}

	@FXML
	public void piuInteroAction(ActionEvent event) {
		numBigliettiInteri++;
		interoTextField.setText(numBigliettiInteri.toString());
	}

	@FXML
	public void menoInteroAction(ActionEvent event) {
		if (numBigliettiInteri > 0)
			numBigliettiInteri--;
		interoTextField.setText(numBigliettiInteri.toString());
	}

	// viene usato anche per aggiornare il prezzo totale
	public void blockCompraButton() {
		prezzo = (tariffa.getPrezzoIntero() * numBigliettiInteri) + (tariffa.getPrezzoRidotto() * numBigliettiRidotti)
				+ "€";
		prezzoLabel.setText(prezzo.toString());
		if (numBigliettiRidotti > 0 || numBigliettiInteri > 0)
			compraButton.setDisable(false);
	}

	@FXML
	public void CompraBigliettoAction(ActionEvent event) throws BusinessException {
		try {
			for (int i = 0; i < numBigliettiInteri; i++) {
				Biglietto biglietto = new Biglietto();
				biglietto.setConcerto(tariffa.getConcerto());
				biglietto.setUtente(metodo.getUtente());
				biglietto.setPrezzo(tariffa.getPrezzoIntero());
				bigliettoService.prenotaBiglietto(biglietto);
			}
			for (int i = 0; i < numBigliettiRidotti; i++) {
				Biglietto biglietto = new Biglietto();
				biglietto.setConcerto(tariffa.getConcerto());
				biglietto.setUtente(metodo.getUtente());
				biglietto.setPrezzo(tariffa.getPrezzoRidotto());
				bigliettoService.prenotaBiglietto(biglietto);
			}
			dispatcher.closeWindowView();
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
