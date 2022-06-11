package it.univaq.disim.oop.roc.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Artista;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;

public interface TourService {

	void add(Artista artista, Set<Concerto> concerti);

	List<Tour> findAllTours() throws BusinessException;
}
