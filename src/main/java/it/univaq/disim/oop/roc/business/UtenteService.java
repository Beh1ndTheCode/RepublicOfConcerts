package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface UtenteService {

	Utente authenticate(String username, String password) throws BusinessException;

	Utente registration(Spettatore spettatore) throws BusinessException;

	void updateDati(Spettatore spettatore) throws BusinessException;

	Utente findUtenteById(int id) throws BusinessException;

}