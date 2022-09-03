package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.Utility;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;

public class RAMConcertoServiceImpl implements ConcertoService {

	private static List<Concerto> concertiAggiunti = new ArrayList<>();

	private static int contNumBiglietti = 0;


	@Override
	public Concerto addConcerto(String artista, Luogo luogo, String giorno, String mese, String anno)
			throws BusinessException {
		try {
			LocalDate data = Utility.VerificaData(giorno, mese, anno);
			Concerto concerto = new Concerto();
			concerto.setArtista(artista);
			concerto.setLuogo(luogo);
			concerto.setData(data);
			concertiAggiunti.add(concerto);

			return concerto;

		} catch (IntegerFormatException e) {
			throw new IntegerFormatException();
		} catch (InvalidDateException e) {
			throw new InvalidDateException();
		}

	}

	@Override
	public void updateConcerto(Concerto concerto, String scaletta, String artista, TipoMetodoDiPagamento metodo,
			String giorno, String mese, String anno, Luogo luogo) throws BusinessException {
		if (giorno.isEmpty() || mese.isEmpty() || anno.isEmpty()) {
			if (!(giorno.isEmpty() && mese.isEmpty() && anno.isEmpty()))
				throw new InvalidDateException();
		} else {
			try {
				LocalDate data = Utility.VerificaData(giorno, mese, anno);
				concerto.setData(data);
			} catch (IntegerFormatException e) {
				throw new IntegerFormatException();
			} catch (InvalidDateException e) {
				throw new InvalidDateException();
			}
		}

		if (!scaletta.isEmpty())
			concerto.setScaletta(scaletta);
		if (!artista.isEmpty() && concerto.getTour() == null)
			concerto.setArtista(artista);
		concerto.setLuogo(luogo);
		concerto.setMetodo(metodo);

		return;
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
		concertiAggiunti.remove(concerto);
		return;
	}

	public Biglietto bookBiglietto(Concerto concerto, Settore settore, Spettatore spettatore) {
		Biglietto biglietto = new Biglietto();
		biglietto.setSettore(settore);
		biglietto.setNumeroBiglietto(contNumBiglietti++);
		biglietto.setSpettatore(spettatore);

		return biglietto;
	}

}
