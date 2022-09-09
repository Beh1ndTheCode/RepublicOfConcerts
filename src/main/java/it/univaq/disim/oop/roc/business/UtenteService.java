package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface UtenteService {

	Utente authenticate(String username, String password) throws BusinessException;

	// Viene restituito un Utente per passare alla schermata Home dopo la registrazione
	Utente registration(Spettatore spettatore) throws BusinessException;

	// Viene invocato sia quando si aggiornano dei dati personali sia quando si cambia meotdo preferito
	void updateDati(Spettatore spettatore) throws BusinessException;

	Utente findUtenteById(int id) throws BusinessException;

}