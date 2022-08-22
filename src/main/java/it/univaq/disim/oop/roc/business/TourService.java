package it.univaq.disim.oop.roc.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface TourService {

	void add(String artista, Set<Concerto> concerti);

	List<Tour> findAllTours() throws BusinessException;
}
