package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;

public class RAMMetodiServiceImpl implements MetodiService {

	private static List<MetodoDiPagamento> metodiAggiunti = new ArrayList<>();
	private static int idCounterMetodi = 1;

	@Override
	public Carta addCarta(Carta carta, String nomeCarta, String intestatario, Integer numero, Integer meseScadenza,
			Integer annoScadenza, Integer cvv) throws BusinessException {
		String cvvControl = String.valueOf(cvv);
		String meseScadenzaControl = String.valueOf(meseScadenza);
		String annoScadenzaControl = String.valueOf(annoScadenza);
		String numeroControl = String.valueOf(numero);
		if (cvvControl.length() == 3 && meseScadenzaControl.length() == 2 && annoScadenzaControl.length() == 2
				&& numeroControl.length() == 16) {
			if ((meseScadenza >= 01 && meseScadenza <= 12) && (annoScadenza >= 00 && annoScadenza <= 99)) {
				carta.setId(idCounterMetodi++);
				carta.setNome(nomeCarta);
				carta.setIntestatario(intestatario);
				carta.setNumero(numero);
				carta.setMeseScadenza(meseScadenza);
				carta.setAnnoScadenza(annoScadenza);
				carta.setCvv(cvv);
				metodiAggiunti.add(carta);
				return carta;
			}
		}
		throw new IntegerFormatException();
	}

	@Override
	public Conto addConto(Conto conto, String nomeConto, String iban) throws BusinessException {
		if (iban.length() == 27) {
			conto.setId(idCounterMetodi++);
			conto.setNome(nomeConto);
			conto.setIban(iban);
			metodiAggiunti.add(conto);
			return conto;
		}
		throw new IntegerFormatException();
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
