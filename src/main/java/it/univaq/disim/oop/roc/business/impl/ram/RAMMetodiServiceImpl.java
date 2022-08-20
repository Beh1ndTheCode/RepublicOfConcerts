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
	public void addCarta(String nomeCarta, String intestatario, String numero, String meseScadenza, String annoScadenza,
			String cvv) throws BusinessException {
		if (cvv.length() == 3 && (meseScadenza.length() == 1 || meseScadenza.length() == 2)
				&& (annoScadenza.length() == 1 || annoScadenza.length() == 2) && numero.length() == 16) {
			Integer meseScadenzaInput, annoScadenzaInput, cvvInput;
			Long numeroInput;

			try {
				numeroInput = Long.parseLong(numero);
				meseScadenzaInput = Integer.parseInt(meseScadenza);
				annoScadenzaInput = Integer.parseInt(annoScadenza);
				cvvInput = Integer.parseInt(cvv);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}

			if ((meseScadenzaInput >= 1 && meseScadenzaInput <= 12)
					&& (annoScadenzaInput >= 0 && annoScadenzaInput <= 99)) {
				Carta carta = new Carta();
				carta.setId(idCounterMetodi++);
				carta.setNome(nomeCarta);
				carta.setIntestatario(intestatario);
				carta.setNumero(numeroInput);
				carta.setMeseScadenza(meseScadenzaInput);
				carta.setAnnoScadenza(annoScadenzaInput);
				carta.setCvv(cvvInput);
				metodiAggiunti.add(carta);

				return;
			}
		}

		throw new IntegerFormatException();
	}

	@Override
	public void addConto(String nomeConto, String iban) throws BusinessException {
		if (iban.length() == 27) {
			Conto conto = new Conto();
			conto.setId(idCounterMetodi++);
			conto.setNome(nomeConto);
			conto.setIban(iban);
			metodiAggiunti.add(conto);
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

	/*
	 * @Override public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws
	 * BusinessException { return new ArrayList<>(metodiAggiunti); }
	 */

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> metodoDiPagamento = new ArrayList<>();

		Carta cartaProva = new Carta();
		cartaProva.setId(idCounterMetodi++);
		cartaProva.setTipo("Carta");
		cartaProva.setNome("Carta prova");
		cartaProva.setIntestatario("Giovanni Storti");
		cartaProva.setNumero(123456789875432L);
		cartaProva.setMeseScadenza(06);
		cartaProva.setAnnoScadenza(23);
		cartaProva.setCvv(578);
		cartaProva.setUtente(utente);
		metodoDiPagamento.add(cartaProva);

		Conto contoProva = new Conto();
		contoProva.setId(idCounterMetodi++);
		contoProva.setTipo("Conto");
		contoProva.setNome("Conto prova");
		contoProva.setIban("IT85I8284863987145289597634");
		contoProva.setUtente(utente);
		metodoDiPagamento.add(contoProva);

		for (MetodoDiPagamento met : metodiAggiunti) {
			if (met.getUtente() == utente)
				metodoDiPagamento.add(met);
		}

		return metodoDiPagamento;
	}

}
