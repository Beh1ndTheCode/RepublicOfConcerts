package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;

public interface MetodiService {

	void addCarta(Carta carta) throws BusinessException, InvalidDateException;

	void addConto(Utente utente, String nomeConto, String iban, String swift) throws BusinessException;

	void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException;

	List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException;

}
