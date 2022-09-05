package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMRecensioniServiceImpl implements RecensioniService {

	private static List<Recensione> recensioniAggiunte = new ArrayList<>();

	private static int idCounterRecensioni = 0;

	@Override
	public void addRecensione(Recensione recensione) throws BusinessException {
		recensione.setId(idCounterRecensioni++);
		recensioniAggiunte.add(recensione);
	}

	@Override
	public void updateRecensione(Recensione recensione) throws BusinessException {
		for (Recensione review : recensioniAggiunte) {
			if (recensione.getId() == review.getId()) {
				review.setTitolo(recensione.getTitolo());
				review.setDescrizione(recensione.getDescrizione());
				review.setValutazione(recensione.getValutazione());
				review.setApprovato(recensione.getApprovato());
				return;
			}
		}
	}

	@Override
	public void deleteRecensione(Recensione recensione) throws BusinessException {
		for (Recensione review : recensioniAggiunte) {
			if (recensione.getId() == review.getId()) {
				recensioniAggiunte.remove(recensione);
				return;
			}
		}
	}

	@Override
	public List<Recensione> findRecensioniByConcerto(Concerto concerto) throws BusinessException {
		List<Recensione> recensioniConcerto = new ArrayList<>();

		for (Recensione review : recensioniAggiunte) {
			if (review.getConcerto().equals(concerto))
				recensioniConcerto.add(review);
		}

		return recensioniConcerto;
	}

	@Override
	public List<Recensione> findRecensioniByUtente(Utente utente) throws BusinessException {
		List<Recensione> recensioniUtente = new ArrayList<>();

		for (Recensione review : recensioniAggiunte) {
			if (review.getUtente().equals(utente))
				recensioniUtente.add(review);
		}

		return recensioniUtente;
	}

	@Override
	public List<Recensione> findRecensioniInAttesa() throws BusinessException {
		List<Recensione> recensioniInAttesa = new ArrayList<>();

		for (Recensione review : recensioniAggiunte) {
			if (!review.getApprovato()) {
				recensioniInAttesa.add(review);
			}
		}

		return recensioniInAttesa;
	}

}
