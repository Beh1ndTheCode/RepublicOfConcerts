package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.domain.Artista;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;

public class RAMTourServiceImpl implements TourService {

	private Set<Tour> tours = new HashSet<>();

	@Override
	public Tour add(Artista artista, Set<Concerto> concerti) {
		Tour tour = new Tour();
		tour.setArtista(artista);
		tour.setConcerti(concerti);
		return tour;
	}

	@Override
	public List<Tour> findAllTours() throws BusinessException {
		return new ArrayList<>(tours);
	}

}
