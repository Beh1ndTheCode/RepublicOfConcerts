package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;

public interface TariffeService {

	void addTariffe(Concerto concerto) throws BusinessException;

	void setTariffa(Concerto concerto, Settore settore, Tariffa tariffa, String prezzo)
			throws BusinessException, FloatFormatException;

	List<Tariffa> findTariffeByConcerto(Concerto concerto) throws BusinessException;

}
