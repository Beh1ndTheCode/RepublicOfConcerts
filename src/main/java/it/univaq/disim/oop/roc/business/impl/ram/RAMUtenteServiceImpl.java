package it.univaq.disim.oop.roc.business.impl.ram;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class RAMUtenteServiceImpl implements UtenteService {

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
		if ("spettatore".equalsIgnoreCase(username)) {
			Utente spettatore = new Spettatore();
			spettatore.setUsername(username);
			spettatore.setPassword(password);
			spettatore.setNome("luigi");
			spettatore.setCognome("bianchi");
			return spettatore;
		}
		throw new UtenteNotFoundException();
	}

	@Override
	public Spettatore registration(String username, String password, String confermaPassword, String nome,
			String cognome, Integer eta) throws BusinessException {
		if (eta > 0 && eta < 100) {
			if (password.equals(confermaPassword)) {
				Spettatore spettatore = new Spettatore();
				spettatore.setUsername(username);
				spettatore.setPassword(password);
				spettatore.setNome(nome);
				spettatore.setCognome(cognome);
				return spettatore;
			}
			throw new InvalidPasswordException();
		}
		throw new EtaFormatException();
	}

}
