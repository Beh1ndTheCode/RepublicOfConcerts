package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface RecensioniService {

	void addRecensione(Recensione recensione) throws BusinessException;

	// Utilizzato sia per aggiornare che per approvare una recensione
	void updateRecensione(Recensione recensione) throws BusinessException;

	// Utilizzato sia per eliminare che per rifiutare una recensione
	void deleteRecensione(Recensione recensione) throws BusinessException;

	List<Recensione> findRecensioniByConcerto(Concerto concerto) throws BusinessException;

	List<Recensione> findRecensioniByUtente(Utente utente) throws BusinessException;

	List<Recensione> findRecensioniInAttesa() throws BusinessException;

}
