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
	public void addTour(String artista, String nome) {
		Tour tour = new Tour();
		tour.setId(idCounterTour++);
		tour.setArtista(artista);
		tour.setNome(nome);
		tourAggiunti.add(tour);

		return;
	}

	@Override
	public void updateTour(Tour tour, String artista, String nome) throws BusinessException {
		if (!artista.isEmpty())
			tour.setArtista(artista);
		if (!nome.isEmpty())
			tour.setNome(nome);

		return;
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
		getTourAggiunti().remove(tour);

		return;
	}

	@Override
	public List<Tour> getTourAggiunti() throws BusinessException {
		return tourAggiunti;
	}

	@Override
	public void addConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		if (tour.getConcerti() == null)
			tour.setConcerti(concerti);
		for (Concerto concert : concerti) {
			concert.setTour(tour);
			concert.setArtista(tour.getArtista());
			if (!tour.getConcerti().contains(concert))
				tour.getConcerti().add(concert);
		}

		return;
	}

	@Override
	public void deleteConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		for (Concerto concert : concerti)
			tour.getConcerti().remove(concert);

		return;
	}
}
