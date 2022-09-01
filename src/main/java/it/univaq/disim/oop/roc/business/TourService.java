package it.univaq.disim.oop.roc.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface TourService {

	void addTour(String artista, String nome);
	
	void updateTour(Tour tour, String artista, String nome);
	
	void deleteTour(Tour tour);
	
	void addConcerto(Tour tour, List<Concerto> concerti);

	List<Tour> findAllTours() throws BusinessException;
}
