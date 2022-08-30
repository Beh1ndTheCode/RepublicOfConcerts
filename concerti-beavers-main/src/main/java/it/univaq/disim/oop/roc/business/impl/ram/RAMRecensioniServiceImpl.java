package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMRecensioniServiceImpl implements RecensioniService {

	private static List<Recensione> recensioniInAttesa = new ArrayList<>();
	private static List<Recensione> recensioniConfermate = new ArrayList<>();

	@Override
	public void addRecensione(Spettatore spettatore, Concerto concerto, String titolo, String descrizione,
			Integer valutazione) throws BusinessException {
		Recensione recensione = new Recensione();

		recensione.setTitolo(titolo);
		recensione.setDescrizione(descrizione);
		recensione.setValutazione(valutazione);
		recensione.setSpettatore(spettatore);
		recensione.setConcerto(concerto);

		recensioniInAttesa.add(recensione);
		return;
	}

	@Override
	public void updateRecensione(Recensione recensione, String titolo, String descrizione, Integer valutazione)
			throws BusinessException {
		recensione.setValutazione(valutazione);
		if (!titolo.equals(null))
			recensione.setTitolo(titolo);
		if (!descrizione.equals(null))
			recensione.setDescrizione(descrizione);

		return;
	}

	@Override
	public void deleteRecensione(Recensione recensione) throws BusinessException {
		recensioniConfermate.remove(recensione);

		return;
	}

	@Override
	public void acceptRecensione(Recensione recensione) throws BusinessException {
		recensioniConfermate.add(recensione);
		recensioniInAttesa.add(recensione);

		return;
	}

	@Override
	public void rejectRecensione(Recensione recensione) throws BusinessException {
		recensioniInAttesa.remove(recensione);

		return;
	}

	@Override
	public List<Recensione> findRecensioniByConcert(Concerto concerto) throws BusinessException {
		List<Recensione> recensioni = new ArrayList<>();

		Recensione recensioneProva = new Recensione();
		recensioneProva.setTitolo("Bellissima esperienza");
		recensioneProva.setDescrizione("Teatro prova");
		recensioneProva.setValutazione(4);
		recensioni.add(recensioneProva);

		for (Recensione review : recensioniConfermate) {
			if (review.getConcerto().equals(concerto))
				recensioni.add(review);
		}

		return recensioni;
	}

	@Override
	public List<Recensione> findRecensioniByUser(Spettatore spettatore) throws BusinessException {
		List<Recensione> recensioni = new ArrayList<>();

		Recensione recensioneProva = new Recensione();
		recensioneProva.setTitolo("Bellissima esperienza");
		recensioneProva.setDescrizione("Teatro prova");
		recensioneProva.setValutazione(5);
		recensioni.add(recensioneProva);

		for (Recensione review : recensioniConfermate) {
			if (review.getSpettatore().equals(spettatore))
				recensioni.add(review);
		}

		return recensioni;
	}

	@Override
	public List<Recensione> findRecensioniInAttesa() throws BusinessException {
		List<Recensione> recensioni = new ArrayList<>();

		for (Recensione review : recensioniInAttesa) {
			recensioni.add(review);
		}

		return recensioni;
	}

}
