package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class RAMUtenteServiceImpl implements UtenteService {

	private static List<Utente> utentiAggiunti = new ArrayList<>();

	private static int idCounterUtenti = 0;

	@Override
	public Utente authenticate(String username, String password) throws BusinessException {
		for (Utente user : utentiAggiunti) {
			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				return user;
			}
		}

		if ("amministratore".equalsIgnoreCase(username)) {
			Utente amministratore = new Amministratore();
			amministratore.setId(idCounterUtenti++);
			amministratore.setUsername(username);
			amministratore.setPassword(password);
			amministratore.setNome("mario");
			amministratore.setCognome("rossi");
			amministratore.setEta(37);
			utentiAggiunti.add(amministratore);

			return amministratore;
		}

		if ("spettatore".equalsIgnoreCase(username)) {
			Spettatore spettatore = new Spettatore();
			spettatore.setId(idCounterUtenti++);
			spettatore.setUsername(username);
			spettatore.setPassword(password);
			spettatore.setNome("luigi");
			spettatore.setCognome("bianchi");
			spettatore.setEta(25);
			utentiAggiunti.add(spettatore);

			return spettatore;
		}

		throw new UtenteNotFoundException();
	}

	@Override
	public Spettatore registration(Spettatore spettatore) throws BusinessException {
		spettatore.setId(idCounterUtenti++);
		utentiAggiunti.add(spettatore);
		return spettatore;
	}

	@Override
	public void updateDati(Spettatore spettatore) throws BusinessException {
		for (Utente user : utentiAggiunti) {
			if (spettatore.getId() == user.getId()) {
				user.setUsername(spettatore.getUsername());
				user.setPassword(spettatore.getPassword());
				user.setNome(spettatore.getNome());
				user.setCognome(spettatore.getCognome());
				user.setEta(spettatore.getEta());
			}
		}
	}

}
