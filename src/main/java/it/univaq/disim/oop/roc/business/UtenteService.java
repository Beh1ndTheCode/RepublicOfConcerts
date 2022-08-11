package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;

	Utente registration(String username, String password, String confermaPassword, String nome, String cognome,
			Integer eta) throws EtaFormatException, InvalidPasswordException, BusinessException;

	void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo, String descrizione)
			throws BusinessException;

	void addConto(String nome, Spettatore spettatore, String iban) throws BusinessException;

	void addCarta(String nome, Spettatore spettatore, Integer numero, Integer meseScadenza, Integer annoScadenza)
			throws BusinessException;

}