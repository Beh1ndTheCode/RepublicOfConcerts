package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface LuogoService {

	void addLuogo(Luogo luogo) throws BusinessException;

	void updateLuogo(Luogo luogo) throws BusinessException;

	void deleteLuogo(Luogo luogo) throws BusinessException;

	List<Luogo> findAllLuoghi() throws BusinessException;

	Luogo findLuogoById(int id) throws BusinessException;

	void addSettore(Settore settore) throws BusinessException;

	void updateSettore(Settore settore) throws BusinessException;

	List<Settore> findAllSettori(Luogo luogo) throws BusinessException;

	Settore findSettoreById(int id) throws BusinessException;

	void deleteSettore(Settore settore) throws BusinessException;

	Integer getCapienzaRimanente(Luogo luogo) throws BusinessException;
}
