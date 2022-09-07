package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMConcertoServiceImpl implements ConcertoService {

	private static List<Concerto> concertiAggiunti = new ArrayList<>();

	private static int idCounterConcerti = 0;

	@Override
	public Concerto addConcerto(Concerto concerto) throws BusinessException {
		concerto.setId(idCounterConcerti++);
		concertiAggiunti.add(concerto);
		return concerto;
	}

	@Override
	public void updateConcerto(Concerto concerto) throws BusinessException {
		for (Concerto concert : concertiAggiunti) {
			if (concerto.getId() == concert.getId()) {
				concert.setArtista(concert.getArtista());
				concert.setLuogo(concerto.getLuogo());
				concert.setData(concerto.getData());
				concert.setScaletta(concerto.getScaletta());

				if (!(concerto.getTipoMetodo() == null))
					concert.setTipoMetodo(concerto.getTipoMetodo());

				if (concert.getTour() == null || !(concert.getTour().getId() == concerto.getTour().getId()))
					concert.setTour(concerto.getTour());
			}
		}
	}

	@Override
	public List<Concerto> findAllConcerti() throws BusinessException {
		List<Concerto> concerti = new ArrayList<>();

		for (Concerto concert : concertiAggiunti) {
			concerti.add(concert);
		}

		return concerti;
	}

	@Override
	public List<Concerto> findConcertiByArtista(String artista) throws BusinessException {
		List<Concerto> concertiArtista = new ArrayList<>();

		for (Concerto concert : concertiAggiunti) {
			if (concert.getArtista().toLowerCase().equals(artista.toLowerCase()))
				concertiArtista.add(concert);
		}
		return concertiArtista;
	}
	
	public List<Concerto> findConcertiBySpettatore(Spettatore spettatore) throws BusinessException {
		List<Concerto> concertiUtente = new ArrayList<>();
		
		for (Concerto concert : concertiAggiunti) {
			boolean ripetizione = false;
			for (Biglietto ticket : spettatore.getBigliettiPrenotati()) {
				if (ticket.getConcerto() == concert) {
					for (Concerto checkConcert : concertiUtente)
						if (checkConcert == concert)
							ripetizione = true;
					if (!ripetizione)
					concertiUtente.add(concert);
				}	
			}
		}
		return concertiUtente;
	}

	@Override
	public List<Concerto> findConcertiByTour(Tour tour) throws BusinessException {
		List<Concerto> concertiTour = new ArrayList<>();

		for (Concerto concert : concertiAggiunti) {
			if (!(concert.getTour() == null)) {
				if (concert.getTour().getId() == tour.getId())
					concertiTour.add(concert);
			}
		}
		return concertiTour;
	}

	@Override
	public Concerto findConcertoById(int id) throws BusinessException {
		return concertiAggiunti.get(id);
	}

	@Override
	public void deleteConcerto(Concerto concerto) throws BusinessException {
		for (Concerto concert : concertiAggiunti) {
			if (concerto.getId() == concert.getId()) {
				concertiAggiunti.remove(concerto);
				return;
			}
		}
	}

}
