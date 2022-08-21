package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface MetodiService {

	void addCarta(Utente utente, String nomeCarta, String intestatario, String numero, String meseScadenza,
			String annoScadenza, String cvv) throws BusinessException;

	void addConto(Utente utente, String nomeConto, String iban, String swift) throws BusinessException;

	void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException;

	List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException;

}
