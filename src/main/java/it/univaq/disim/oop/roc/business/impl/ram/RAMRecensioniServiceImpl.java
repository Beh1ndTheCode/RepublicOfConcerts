package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.HashSet;
import java.util.Set;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMRecensioniServiceImpl implements RecensioniService {

	private static Set<Recensione> recensioni = new HashSet<>();

	@Override
	public void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo,
			String descrizione) throws BusinessException {
		recensione.setSpettatore(spettatore);
		recensione.setConcerto(concerto);
		recensione.setTitolo(titolo);
		recensione.setDescrizione(descrizione);
		recensioni.add(recensione);
	}

}
