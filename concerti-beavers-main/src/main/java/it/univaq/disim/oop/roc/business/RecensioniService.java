package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface RecensioniService {

	void addRecensione(Spettatore spettatore, Concerto concerto, String titolo, String descrizione, Integer valutazione)
			throws BusinessException;

	void updateRecensione(Recensione recensione, String titolo, String descrizione, Integer valutazione)
			throws BusinessException;

	void deleteRecensione(Recensione recensione) throws BusinessException;

	void acceptRecensione(Recensione recensione) throws BusinessException;

	void rejectRecensione(Recensione recensione) throws BusinessException;

	List<Recensione> findRecensioniByConcert(Concerto concerto) throws BusinessException;

	List<Recensione> findRecensioniByUser(Spettatore spettatore) throws BusinessException;

	List<Recensione> findRecensioniInAttesa() throws BusinessException;

}
