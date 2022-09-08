package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
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
	public void updateBiglietto(Biglietto biglietto) throws BusinessException {
		for (Biglietto ticket : bigliettiPrenotati) {
			if (biglietto.getNumeroBiglietto() == ticket.getNumeroBiglietto()) {
				ticket.setPosto(biglietto.getPosto());
			}
		}
	}

	@Override
	public List<Biglietto> findAllBiglietti(Utente utente) throws BusinessException {
		List<Biglietto> biglietti = new ArrayList<>();
		for (Biglietto ticket : bigliettiPrenotati) {
			if (ticket.getUtente().getId() == utente.getId())
				biglietti.add(ticket);
		}
		return biglietti;
	}

	public List<Biglietto> findBigliettiByConcertoAndUtente(Concerto concerto, Utente utente) throws BusinessException {
		List<Biglietto> biglietti = new ArrayList<>();
		for (Biglietto ticket : bigliettiPrenotati) {
			if (ticket.getUtente().getId() == utente.getId() && ticket.getConcerto().getId() == concerto.getId()) {
				biglietti.add(ticket);
			}
		}
		return biglietti;
	}

	public List<Biglietto> findBigliettiByConcertoAndSettore(Concerto concerto, Settore settore)
			throws BusinessException {
		List<Biglietto> biglietti = new ArrayList<>();
		for (Biglietto ticket : bigliettiPrenotati) {
			if (ticket.getSettore().getId() == settore.getId() && ticket.getConcerto().getId() == concerto.getId()) {
				biglietti.add(ticket);
			}
		}
		return biglietti;
	}

}
