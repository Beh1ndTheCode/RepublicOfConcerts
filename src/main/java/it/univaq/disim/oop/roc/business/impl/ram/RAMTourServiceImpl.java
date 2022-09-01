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
	public List<Tour> findAllTours() throws BusinessException {
		List<Tour> tours = new ArrayList<>();

		Tour tourProva = new Tour();
		tourProva.setArtista("Gemitaiz e Medman");
		tourProva.setNome("Scatola nera");
		tourProva.setConcerti(null);
		tours.add(tourProva);

		for (Tour tour : tourAggiunti) {
			tours.add(tour);
		}

		return tours;
	}
	
	public void updateTour(Tour tour, String artista, String nome) {
		if (!artista.isEmpty()) 
			tour.setArtista(artista);
		if (!nome.isEmpty())
			tour.setNome(nome);
	}
	
	public void deleteTour(Tour tour) {
		getTourAggiunti().remove(tour);
	}

	public static List<Tour> getTourAggiunti() {
		return tourAggiunti;
	}
	
	public void addConcerto(Tour tour, List<Concerto> concerti) {
		
	}

}
