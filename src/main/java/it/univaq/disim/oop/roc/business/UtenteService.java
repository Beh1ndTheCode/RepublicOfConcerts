package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;

	Utente registration(String username, String password, String nome, String cognome, Integer eta)
			throws BusinessException;

	void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo, String descrizione)
			throws BusinessException;

	/*
	// TROVARE IL MODO DI SEPARARE GLI INPUT DI BONIFICI E CARTE
	void addMetodo(String tipo, MetodoDiPagamento metodo, String nome) throws
	  BusinessException;
	*/
}