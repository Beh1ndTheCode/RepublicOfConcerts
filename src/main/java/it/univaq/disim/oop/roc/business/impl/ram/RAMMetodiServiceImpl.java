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

public class RAMMetodiServiceImpl implements MetodiService {

	private static List<MetodoDiPagamento> metodiAggiunti = new ArrayList<>();
	private static int idCounterMetodi = 0;

	@Override
	public void addMetodo(MetodoDiPagamento metodo) throws BusinessException {
		metodo.setId(idCounterMetodi++);
		metodiAggiunti.add(metodo);
	}

	@Override
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {
		metodiAggiunti.remove(metodo);
	}

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> metodiDiPagamento = new ArrayList<>();

		Carta cartaProva = new Carta();
		cartaProva.setId(idCounterMetodi++);
		cartaProva.setNome("Carta prova");
		cartaProva.setIntestatario("Giovanni Storti");
		cartaProva.setNumero(1234567890123456l);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = "22/09/2022";
		LocalDate localDate = LocalDate.parse(data, formatter);
		cartaProva.setScadenza(localDate);
		cartaProva.setCvv(578);
		cartaProva.setUtente(utente);
		metodiDiPagamento.add(cartaProva);

		Conto contoProva = new Conto();
		contoProva.setId(idCounterMetodi++);
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
