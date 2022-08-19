package it.univaq.disim.oop.roc.business.impl.ram;

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
	private static int idCounterMetodi = 1;

	@Override
	public Carta addCarta(Carta carta, String nomeCarta, String intestatario, String numero, String scadenza,
			Integer cvv) throws BusinessException {
		carta.setId(idCounterMetodi++);
		carta.setNome(nomeCarta);
		carta.setIntestatario(intestatario);
		carta.setNumero(numero);
		carta.setScadenza(scadenza);
		carta.setCvv(cvv);
		metodiAggiunti.add(carta);
		return carta;
	}

	@Override
	public Conto addConto(Conto conto, String nomeConto, String iban) throws BusinessException {
		conto.setId(idCounterMetodi++);
		conto.setNome(nomeConto);
		conto.setIban(iban);
		metodiAggiunti.add(conto);
		return conto;
	}

	@Override
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {
		for (MetodoDiPagamento met : metodiAggiunti) {
			if (metodo.getId() == met.getId()) {
				metodiAggiunti.remove(met);
			}
		}
	}

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		return new ArrayList<>(metodiAggiunti);
	}

}
