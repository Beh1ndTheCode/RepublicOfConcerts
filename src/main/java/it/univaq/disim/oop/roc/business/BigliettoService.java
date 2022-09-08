package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface BigliettoService {

	void prenotaBiglietto(Biglietto biglietto) throws BusinessException;
	
	void updatePostoBiglietto(Biglietto biglietto) throws BusinessException;

	List<Biglietto> findAllBiglietti(Utente utente) throws BusinessException;
	
	List<Biglietto> findBigliettiByConcertoAndSpettatore(Concerto concerto,Utente utente) throws BusinessException;
	
	List<Biglietto> findBigliettiByConcertoAndSettore(Concerto concerto, Settore settore) throws BusinessException;

}
