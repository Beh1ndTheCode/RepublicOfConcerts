package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMConcertoServiceImpl implements ConcertoService {

	private List<Concerto> concertiAggiunti = new ArrayList<>();

	private static int contNumBiglietti = 1;

	@Override
	public void addConcerto(Luogo luogo, LocalDate data) {
		Concerto concerto = new Concerto();
		concerto.setLuogo(luogo);
		concerto.setData(data);

		return;
	}

	public void updateConcerto(Concerto concerto, String scaletta, MetodoDiPagamento metodo) {
		concerto.setScaletta(scaletta);
		concerto.setMetodo(metodo);

		return;
	}

	@Override
	public List<Concerto> findAllConcerti() throws BusinessException {
		List<Concerto> concerti = new ArrayList<>();

		Concerto concertoProva = new Concerto();
		LocalDate date = LocalDate.of(2022, 9, 26);
		Teatro teatro = new Teatro();
		teatro.setCitta("Roma");

		concertoProva.setData(date);
		concertoProva.setLuogo(teatro);
		concertoProva.setArtista("Renato Zero");
		concertoProva.setScaletta("La scala musicale è definibile come un sistema di organizzazione"
				+ " dei suoni sviluppato nel contesto teorico e/o nella pratica"
				+ " da ogni cultura musicale, passata e presente.");
		concerti.add(concertoProva);

		for (Concerto concert : concertiAggiunti) {
			concerti.add(concert);
		}

		return concerti;
	}

	public Biglietto bookBiglietto(Concerto concerto, Settore settore, Spettatore spettatore) {
		Biglietto biglietto = new Biglietto();
		biglietto.setSettore(settore);
		biglietto.setNumeroBiglietto(contNumBiglietti++);
		biglietto.setSpettatore(spettatore);

		return biglietto;
	}
}
