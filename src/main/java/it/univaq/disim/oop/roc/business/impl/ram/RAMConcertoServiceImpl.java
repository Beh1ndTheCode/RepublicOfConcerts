package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMConcertoServiceImpl implements ConcertoService {

	private static List<Concerto> concertiAggiunti = new ArrayList<>();
	private static List<Biglietto> bigliettiPrenotati = new ArrayList<>();

	private static int idCounterConcerti = 0;
	private static int contNumBiglietti = 0;

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
				concert.setTipoMetodo(concerto.getTipoMetodo());
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
	public void deleteConcerto(Concerto concerto) throws BusinessException {
		for (Concerto concert : concertiAggiunti) {
			if (concerto.getId() == concert.getId()) {
				concertiAggiunti.remove(concerto);
				return;
			}
		}
	}

	@Override
	public void prenotaBiglietto(Biglietto biglietto) throws BusinessException {
		biglietto.setNumeroBiglietto(contNumBiglietti++);
		bigliettiPrenotati.add(biglietto);
	}

}
