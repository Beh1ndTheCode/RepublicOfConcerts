package it.univaq.disim.oop.roc.controller.finestre.spettatore;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.controller.DataInitializable;
import it.univaq.disim.oop.roc.controller.UtenteInitializable;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.TipologiaBiglietto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;
import it.univaq.disim.oop.roc.viste.ViewException;
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
	
	//disabilita il bottone Compra e imposta tutti i numeri a 0
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

	@FXML	//aumenta il numero di biglietti ridotti
	public void piuRidottoAction(ActionEvent event) {
		numBigliettiRidotti++;
		ridottoTextField.setText(numBigliettiRidotti.toString());
	}

	@FXML	//diminuisce il numero di biglietti ridotti verificando che non scenda sotto lo 0
	public void menoRidottoAction(ActionEvent event) {
		if (numBigliettiRidotti > 0)
			numBigliettiRidotti--;
		ridottoTextField.setText(numBigliettiRidotti.toString());
	}

	@FXML	//aumenta il numero di biglietti interi
	public void piuInteroAction(ActionEvent event) {
		numBigliettiInteri++;
		interoTextField.setText(numBigliettiInteri.toString());
	}

	@FXML	//diminuisce il numero di biglietti interi verificando che non scenda sotto lo 0
	public void menoInteroAction(ActionEvent event) {
		if (numBigliettiInteri > 0)
			numBigliettiInteri--;
		interoTextField.setText(numBigliettiInteri.toString());
	}

	//sblocca il pulsante Compra se è inserito almeno un biglietto
	//e viene usato anche per aggiornare il prezzo totale
	public void blockCompraButton() {
		prezzo = (tariffa.getPrezzoIntero() * numBigliettiInteri) + (tariffa.getPrezzoRidotto() * numBigliettiRidotti)
				+ "€";
		prezzoLabel.setText(prezzo.toString());
		if (numBigliettiRidotti > 0 || numBigliettiInteri > 0)
			compraButton.setDisable(false);
	}

			// si attiva con il pulsante Compra, verifica se ci sono posti nel settore, se ci sono
			// crea, inizializza e assegna automaticamente dei posti liberi ai biglietti
	@FXML	// e apre la vista di selezione dei posti
	public void CompraBigliettoAction(ActionEvent event) throws BusinessException {
		if (tariffa.getSettore()
				.getCapienza() < (bigliettoService
						.findBigliettiByConcertoAndSettore(tariffa.getConcerto(), tariffa.getSettore()).size()
						+ numBigliettiInteri + numBigliettiRidotti))
			errorLabel.setText("posti tutti occupati, cambia settore e riprova");
		else {
			List<Biglietto> bigliettiAggiunti = new ArrayList<>();
			List<Biglietto> bigliettiEsistenti = new ArrayList<>();
			bigliettiEsistenti = bigliettoService.findBigliettiByConcertoAndSettore(tariffa.getConcerto(),
					tariffa.getSettore());

			for (int i = 1; i <= numBigliettiInteri; i++) {
				Integer posto = 0;
				for (int j = 1; j <= tariffa.getSettore().getCapienza(); j++) {
					Boolean postoLibero = true;
					for (Biglietto ticket : bigliettiEsistenti) {
						if (ticket.getPosto() == j) {
							postoLibero = false;
							break;
						}
					}
					if (postoLibero) {
						posto = j;
						break;
					}
				}
				Biglietto biglietto = new Biglietto();
				biglietto.setConcerto(tariffa.getConcerto());
				biglietto.setSettore(tariffa.getSettore());
				biglietto.setUtente(metodo.getUtente());
				biglietto.setPrezzo(tariffa.getPrezzoIntero());
				biglietto.setTipologiaBiglietto(TipologiaBiglietto.Intero);
				biglietto.setPosto(posto);
				bigliettoService.prenotaBiglietto(biglietto);
				bigliettiEsistenti.add(biglietto);
				bigliettiAggiunti.add(biglietto);
			}

			for (int i = 1; i <= numBigliettiRidotti; i++) {
				int posto = 0;
				for (int j = 1; j <= tariffa.getSettore().getCapienza(); j++) {
					Boolean postoLibero = true;
					for (Biglietto ticket : bigliettiEsistenti) {
						if (ticket.getPosto() == j) {
							postoLibero = false;
							break;
						}
					}
					if (postoLibero) {
						posto = j;
						break;
					}
				}
				Biglietto biglietto = new Biglietto();
				biglietto.setConcerto(tariffa.getConcerto());
				biglietto.setSettore(tariffa.getSettore());
				biglietto.setUtente(metodo.getUtente());
				biglietto.setPrezzo(tariffa.getPrezzoRidotto());
				biglietto.setTipologiaBiglietto(TipologiaBiglietto.Ridotto);
				biglietto.setPosto(Integer.valueOf(posto));
				biglietto = bigliettoService.prenotaBiglietto(biglietto);
				bigliettiEsistenti.add(biglietto);
				bigliettiAggiunti.add(biglietto);
			}
			try {
				dispatcher.closeWindowView();
				dispatcher.openNewWindow("selezioneposto", bigliettiAggiunti.get(0), bigliettiAggiunti);
			} catch (ViewException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void closeWindow() {
		dispatcher.closeWindowView();
	}
}
