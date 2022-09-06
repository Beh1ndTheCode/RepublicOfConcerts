package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface TourService {

	void addTour(Tour tour) throws BusinessException;

	void updateTour(Tour tour) throws BusinessException;

	void deleteTour(Tour tour) throws BusinessException;

	List<Tour> findAllTours() throws BusinessException;

}
