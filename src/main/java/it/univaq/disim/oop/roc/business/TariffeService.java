package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface TariffeService {

	void addTariffe(Concerto concerto, Luogo luogo) throws BusinessException;

	void setTariffa(Tariffa tariffa) throws BusinessException;

	List<Tariffa> findAllTariffe(Concerto concerto) throws BusinessException;

	Tariffa findTariffaById(int id) throws BusinessException;
}