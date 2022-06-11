package it.univaq.disim.oop.roc.business;

import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;

public interface LuogoService {

	void add(String tipo, String citta, Set<Settore> settori);

	List<Luogo> findAllLuoghi() throws BusinessException;
}
