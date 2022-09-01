package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface TourService {

	void addTour(String artista, String nome);

	void updateTour(Tour tour, String artista, String nome) throws BusinessException;

	void deleteTour(Tour tour) throws BusinessException;

	void addConcerto(Tour tour, List<Concerto> concerti) throws BusinessException;

	List<Tour> getTourAggiunti() throws BusinessException;

	List<Tour> findAllTours() throws BusinessException;
}
