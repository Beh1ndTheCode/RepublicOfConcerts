package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.TipeFormatException;

public interface LuogoService {

	void addLuogo(String tipo, String nome, String citta, Integer capienza) throws BusinessException, TipeFormatException;

	void addSettore(String nome, Integer capienza, Luogo luogo) throws BusinessException;

	void updateSettore(Settore settore, String nome, Integer capienza) throws BusinessException;

	List<Luogo> findAllLuoghi() throws BusinessException;

	void updateDati(Luogo luogo, String text, String text2, Integer capienza);

}
