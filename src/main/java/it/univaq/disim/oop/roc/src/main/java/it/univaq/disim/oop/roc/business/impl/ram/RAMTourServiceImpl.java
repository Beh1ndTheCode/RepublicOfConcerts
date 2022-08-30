package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMTourServiceImpl implements TourService {

	private Set<Tour> tourAggiunti = new HashSet<>();

	@Override
	public void addTour(String artista, Set<Concerto> concerti) {
		Tour tour = new Tour();
		tour.setArtista(artista);
		tour.setConcerti(concerti);
		tourAggiunti.add(tour);

		return;
	}

	@Override
	public List<Tour> findAllTours() throws BusinessException {
		List<Tour> tours = new ArrayList<>();

		Tour tourProva = new Tour();
		tourProva.setArtista(null);
		tourProva.setConcerti(null);

		for (Tour tour : tourAggiunti) {
			tours.add(tour);
		}

		return tours;
	}

}
