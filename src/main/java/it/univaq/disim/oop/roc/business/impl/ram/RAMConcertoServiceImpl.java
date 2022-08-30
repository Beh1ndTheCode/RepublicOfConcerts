package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMConcertoServiceImpl implements ConcertoService {

	private List<Concerto> concertiAggiunti = new ArrayList<>();

	private static int contNumBiglietti = 1;

	@Override
	public void addConcerto(String artista, String luogo, String data) {
		Concerto concerto = new Concerto();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(data, formatter);
		concerto.setArtista(artista);
		// concerto.setLuogo(luogo);
		concerto.setData(localDate);
		concertiAggiunti.add(concerto);

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "25/09/2022";
		LocalDate localDate = LocalDate.parse(date, formatter);

		Teatro teatro = new Teatro();
		teatro.setCitta("Roma");

		concertoProva.setData(localDate);
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
