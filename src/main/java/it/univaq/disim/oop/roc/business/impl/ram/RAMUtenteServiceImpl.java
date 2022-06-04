package it.univaq.disim.oop.roc.business.impl.ram;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.UtenteNotFoundException;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;

public class RAMUtenteServiceImpl implements UtenteService {

	public RAMUtenteServiceImpl() {

	}

	@Override
	public Utente authenticate(String username, String password) throws BusinessException {
		if ("amministratore".equalsIgnoreCase(username)) {
			Utente amministratore = new Amministratore();
			amministratore.setUsername(username);
			amministratore.setPassword(password);
			amministratore.setNome("mario");
			amministratore.setCognome("rossi");
			return amministratore;
		}
		throw new UtenteNotFoundException();
	}

	@Override
	public Utente registration(String username, String password, String nome, String cognome, Integer eta) {
		Utente spettatore = new Spettatore();
		spettatore.setUsername(username);
		spettatore.setPassword(password);
		spettatore.setNome(nome);
		spettatore.setCognome(cognome);
		return spettatore;
		// prende in input i valori inseriti dall'utente e crea un oggetto spettatore
	}

}
