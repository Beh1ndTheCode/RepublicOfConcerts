package it.univaq.disim.oop.roc.business.impl;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface RecensioniService {

	void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo, String descrizione)
			throws BusinessException;

}
