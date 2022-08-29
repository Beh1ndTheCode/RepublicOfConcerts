package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBounds;

public interface LuogoService {

	void addLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException;

	void updateLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException;

	void deleteLuogo(Luogo luogo) throws BusinessException;

	void verificaCapienza(Luogo luogo, Integer capienzaSettore) throws BusinessException, NumberOutOfBounds;

	List<Luogo> findAllLuoghi() throws BusinessException;

	void addSettore(Luogo luogo, String nome, String capienza) throws BusinessException;

	void updateSettore(Settore settore, String nome, String capienza) throws BusinessException;

	List<Settore> findAllSettori(Luogo luogo) throws BusinessException;

	void deleteSettore(Settore settore) throws BusinessException;
}
