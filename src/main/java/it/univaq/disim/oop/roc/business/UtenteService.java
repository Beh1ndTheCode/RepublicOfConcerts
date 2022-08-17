package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;

	Utente registration(String username, String password, String confermaPassword, String nome, String cognome,
			Integer eta) throws EtaFormatException, InvalidPasswordException, BusinessException;

	public void updateDati(Utente utente, String name, String surname, String username, Integer age, String oldPassword,
			String newPassword, String repeatPassword)
			throws EtaFormatException, UtenteNotFoundException, InvalidPasswordException, BusinessException;
}