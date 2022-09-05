package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMTourServiceImpl implements TourService {

	private static List<Tour> tourAggiunti = new ArrayList<>();

	private static int idCounterTour = 0;

	@Override
	public void addTour(Tour tour) {
		tour.setId(idCounterTour++);
		tourAggiunti.add(tour);
	}

	@Override
	public void updateTour(Tour tour) throws BusinessException {
		for (Tour t : tourAggiunti) {
			if (tour.getId() == t.getId()) {
				t.setArtista(tour.getArtista());
				t.setNome(tour.getNome());
			}
		}
	}

	@Override
	public List<Tour> findAllTours() throws BusinessException {
		List<Tour> tours = new ArrayList<>();

		for (Tour tour : tourAggiunti) {
			tours.add(tour);
		}

		return tours;
	}

	@Override
	public void deleteTour(Tour tour) throws BusinessException {
		for (Tour t : tourAggiunti) {
			if (t.getId() == tour.getId()) {
				tourAggiunti.remove(tour);
				return;
			}
		}
	}

	@Override
	public void addConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		for (Concerto concert : concerti) {
			if (concert.getTour() == null || !(concert.getTour().getId() == tour.getId())) {
				concert.setTour(tour);
			}
		}
	}

	@Override
	public void deleteConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		for (Concerto concert : concerti)
			concert.setTour(null);
	}

}
