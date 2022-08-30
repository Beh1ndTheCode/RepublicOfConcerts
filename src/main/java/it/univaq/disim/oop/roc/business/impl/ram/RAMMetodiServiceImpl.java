package it.univaq.disim.oop.roc.business.impl.ram;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;

public class RAMMetodiServiceImpl implements MetodiService {

	private static List<MetodoDiPagamento> metodiAggiunti = new ArrayList<>();
	private static int idCounterMetodi = 1;

	@Override
	public void addCarta(Utente utente, String nomeCarta, String intestatario, String numero, String meseScadenza,
			String annoScadenza, String cvv) throws BusinessException {
		if (cvv.length() == 3 && numero.length() == 16 && meseScadenza.length() == 2 && annoScadenza.length() == 2) {
			Integer cvvInput, meseInput, annoInput;
			Long numeroInput;

			try {
				numeroInput = Long.parseLong(numero);
				cvvInput = Integer.parseInt(cvv);
				meseInput = Integer.parseInt(meseScadenza);
				annoInput = Integer.parseInt(annoScadenza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}

			if (!(meseInput <= 12))
				throw new InvalidDateException();
			Carta carta = new Carta();
			carta.setId(idCounterMetodi++);
			carta.setTipo("Carta");
			carta.setNome(nomeCarta);
			carta.setUtente(utente);
			carta.setIntestatario(intestatario);
			carta.setNumero(numeroInput);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
			String date = ("01/" + meseInput + "/" + annoInput);
			LocalDate localDate = LocalDate.parse(date, formatter);
			carta.setScadenza(localDate);
			carta.setCvv(cvvInput);
			metodiAggiunti.add(carta);

			return;
		}

		throw new IntegerFormatException();
	}

	@Override
	public void addConto(Utente utente, String nomeConto, String iban, String swift) throws BusinessException {
		if (iban.length() == 27) {
			Conto conto = new Conto();
			conto.setId(idCounterMetodi++);
			conto.setTipo("Conto");
			conto.setNome(nomeConto);
			conto.setIban(iban);
			conto.setSwift(swift);
			conto.setUtente(utente);
			metodiAggiunti.add(conto);

			return;
		}

		throw new IntegerFormatException();
	}

	@Override
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {
		metodiAggiunti.remove(metodo);

		return;
	}

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> metodiDiPagamento = new ArrayList<>();

		Carta cartaProva = new Carta();
		cartaProva.setId(idCounterMetodi++);
		cartaProva.setTipo("Carta");
		cartaProva.setNome("Carta prova");
		cartaProva.setIntestatario("Giovanni Storti");
		cartaProva.setNumero(1234567890123456l);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		String date = "01/09/22";
		LocalDate localDate = LocalDate.parse(date, formatter);
		cartaProva.setScadenza(localDate);
		cartaProva.setCvv(578);
		cartaProva.setUtente(utente);
		metodiDiPagamento.add(cartaProva);

		Conto contoProva = new Conto();
		contoProva.setId(idCounterMetodi++);
		contoProva.setTipo("Conto");
		contoProva.setNome("Conto prova");
		contoProva.setIban("IT85I8284863987145289597634");
		contoProva.setUtente(utente);
		metodiDiPagamento.add(contoProva);

		for (MetodoDiPagamento method : metodiAggiunti) {
			if (method.getUtente().equals(utente))
				metodiDiPagamento.add(method);
		}

		return metodiDiPagamento;
	}

}
