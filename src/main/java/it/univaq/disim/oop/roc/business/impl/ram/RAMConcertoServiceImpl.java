package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Artista;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Tour;

public class RAMConcertoServiceImpl implements ConcertoService {

	private Set<Concerto> concerti = new HashSet<>();

	@Override
	public void add(String scaletta, MetodoDiPagamento metodo, Tour tour, Luogo luogo, LocalDate dataConcerto,
			Set<Artista> artisti, Set<Tariffa> tariffe) {
		Concerto concerto = new Concerto();
		concerto.setScaletta(scaletta);
		concerto.setMetodo(metodo);
		concerto.setTour(tour);
		concerto.setLuogo(luogo);
		concerto.setDataConcerto(dataConcerto);
		concerto.setArtista(artisti);
	}

	@Override
	public List<Concerto> findAllConcerti() throws BusinessException {
		return new ArrayList<>(concerti);
	}

}
