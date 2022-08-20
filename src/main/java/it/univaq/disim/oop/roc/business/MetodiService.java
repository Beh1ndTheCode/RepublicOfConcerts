package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface MetodiService {

	void addCarta(String nomeCarta, String intestatario, String numero, String meseScadenza, String annoScadenza,
			String cvv) throws BusinessException;

	void addConto(String nomeConto, String iban) throws BusinessException;

	void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException;

	List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException;

}
