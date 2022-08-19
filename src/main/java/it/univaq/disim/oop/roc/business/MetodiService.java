package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface MetodiService {

	Carta addCarta(Carta carta, String nomeCarta, String intestatario, String numero, String scadenza, Integer cvv)
			throws BusinessException;

	Conto addConto(Conto conto, String nomeConto, String iban) throws BusinessException;

	void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException;

	List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException;

}
