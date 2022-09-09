package it.univaq.disim.oop.roc.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface ConcertoService {

	Concerto addConcerto(Concerto concerto) throws BusinessException;

	void updateConcerto(Concerto concerto) throws BusinessException;

	void deleteConcerto(Concerto concerto) throws BusinessException;

	List<Concerto> findAllConcerti() throws BusinessException;

	// Viene invocato per selezionare un artista nel momento della creazione di un tour
	List<Concerto> findConcertiByArtista(String artista) throws BusinessException;

	List<Concerto> findConcertiByTour(Tour tour) throws BusinessException;

	Set<String> findAllArtisti() throws BusinessException;

	Concerto findConcertoById(int id) throws BusinessException;
}
