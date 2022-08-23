package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface LuogoService {

	void addLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException;

	void addSettore(String nome, Integer capienza, Luogo luogo) throws BusinessException;

	void updateSettore(Settore settore, String nome, Integer capienza) throws BusinessException;

	List<Luogo> findAllLuoghi() throws BusinessException;

	void updateDati(Luogo luogo, String nome, String citta, String capienza) throws BusinessException;

}
