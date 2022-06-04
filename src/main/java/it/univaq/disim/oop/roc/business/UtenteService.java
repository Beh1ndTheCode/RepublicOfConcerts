package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Utente;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;

	Utente registration(String username, String password, String nome, String cognome, Integer eta);
}