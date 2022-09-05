package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMBigliettoServiceImpl implements BigliettoService {

	private static List<Biglietto> bigliettiPrenotati = new ArrayList<>();

	private static int contNumBiglietti = 0;

	@Override
	public void prenotaBiglietto(Biglietto biglietto) throws BusinessException {
		biglietto.setNumeroBiglietto(contNumBiglietti++);
		bigliettiPrenotati.add(biglietto);
	}

	@Override
	public List<Biglietto> findAllBiglietti(Utente utente) throws BusinessException {
		List<Biglietto> biglietti = new ArrayList<>();
		for (Biglietto ticket : bigliettiPrenotati) {
			if (ticket.getUtente().equals(utente))
				biglietti.add(ticket);
		}
		return biglietti;
	}
}
