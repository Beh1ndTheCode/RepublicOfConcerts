package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface LuogoService {

	void add(String tipo, String citta);

	void updateSettore(Settore settore, String nome, Integer capienza);

	List<Luogo> findAllLuoghi() throws BusinessException;
	
}
