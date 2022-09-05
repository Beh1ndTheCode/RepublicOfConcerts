package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface ConcertoService {

	Concerto addConcerto(Concerto concerto) throws BusinessException;

	void updateConcerto(Concerto concerto) throws BusinessException;

	void deleteConcerto(Concerto concerto) throws BusinessException;

	List<Concerto> findAllConcerti() throws BusinessException;

	void prenotaBiglietto(Biglietto biglietto) throws BusinessException;

}
