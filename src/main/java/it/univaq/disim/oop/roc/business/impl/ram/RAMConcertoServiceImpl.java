package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Artista;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Tour;

public class RAMConcertoServiceImpl implements ConcertoService {

	private Set<Concerto> concerti = new HashSet<>();

	private static int contNumBiglietti = 0;

	@Override
	public void addConcerto(MetodoDiPagamento metodo, Tour tour, Luogo luogo, LocalDate dataConcerto,
			Set<Artista> artisti) {
		Concerto concerto = new Concerto();
		concerto.setMetodo(metodo);
		concerto.setTour(tour);
		concerto.setLuogo(luogo);
		concerto.setDataConcerto(dataConcerto);
		concerto.setArtista(artisti);
	}

	public void updateConcerto(Concerto concerto, String scaletta, Set<Tariffa> tariffe) {
		concerto.setScaletta(scaletta);
		concerto.setTariffa(tariffe);
	}

	@Override
	public List<Concerto> findAllConcerti() throws BusinessException {
		return new ArrayList<>(concerti);
	}
	
	//da completare
	public Biglietto bookBiglietto(Biglietto biglietto, Concerto concerto, Settore settore, Spettatore spettatore) {
		biglietto.setConcerto(concerto);
		biglietto.setSettore(settore);
		contNumBiglietti++;
		biglietto.setNumeroBiglietto(contNumBiglietti);
		biglietto.setSpettatore(spettatore);
		return biglietto;
	}
}
