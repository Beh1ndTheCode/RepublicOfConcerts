package it.univaq.disim.oop.roc.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface ConcertoService {

	void addConcerto(MetodoDiPagamento metodo, Tour tour, Luogo luogo, LocalDate dataConcerto, Set<String> artisti);

	void updateConcerto(Concerto concerto, String scaletta, Set<Tariffa> tariffe);

	List<Concerto> findAllConcerti() throws BusinessException;

	//da completare
	public Biglietto bookBiglietto(Biglietto biglietto, Concerto concerto, Settore settore, Spettatore spettatore);
}
