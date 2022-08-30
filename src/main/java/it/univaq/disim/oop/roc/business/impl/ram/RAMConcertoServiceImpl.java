package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;

public class RAMConcertoServiceImpl implements ConcertoService {

	private List<Concerto> concertiAggiunti = new ArrayList<>();

	private static int contNumBiglietti = 1;

	@Override
	public void addConcerto(String artista, Luogo luogo, String giorno, String mese, String anno)
			throws BusinessException {
		if (giorno.length() == 2 && mese.length() == 2 && anno.length() == 2) {

		}
		Integer giornoInput, meseInput, annoInput;

		try {
			giornoInput = Integer.parseInt(giorno);
			meseInput = Integer.parseInt(mese);
			annoInput = Integer.parseInt(anno);
		} catch (NumberFormatException n) {
			throw new IntegerFormatException();
		}

		if (!(giornoInput <= 31 && meseInput <= 12))
			throw new InvalidDateException();
		Concerto concerto = new Concerto();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = giornoInput + "/" + meseInput + "/" + annoInput;
		LocalDate localDate = LocalDate.parse(data, formatter);
		concerto.setArtista(artista);
		concerto.setLuogo(luogo);
		concerto.setData(localDate);
		concertiAggiunti.add(concerto);
		System.out.println(concerto.getLuogo().getNome());

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
