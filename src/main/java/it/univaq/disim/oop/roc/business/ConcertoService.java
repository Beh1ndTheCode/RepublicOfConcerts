package it.univaq.disim.oop.roc.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Artista;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Tour;

public interface ConcertoService {

	void add(String scaletta, MetodoDiPagamento metodo, Tour tour, Luogo luogo, LocalDate dataConcerto,
			Set<Artista> artisti, Set<Tariffa> tariffe);

	List<Concerto> findAllConcerti() throws BusinessException;
}
