package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface MetodiService {

	void addMetodo(MetodoDiPagamento metodo) throws BusinessException;

	void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException;

	List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException;

}
