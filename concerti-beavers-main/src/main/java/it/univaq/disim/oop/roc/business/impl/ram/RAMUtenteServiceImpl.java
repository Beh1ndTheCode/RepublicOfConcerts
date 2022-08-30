package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class RAMUtenteServiceImpl implements UtenteService {

	private static List<Utente> utentiAggiunti = new ArrayList<>();
	private static int idCounterUtenti = 1;

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
	public Spettatore registration(String username, String password, String confermaPassword, String nome,
			String cognome, Integer eta) throws BusinessException {
		if (eta > 0 && eta < 100) {
			if (password.equals(confermaPassword)) {
				Spettatore spettatore = new Spettatore();
				spettatore.setId(idCounterUtenti++);
				spettatore.setUsername(username);
				spettatore.setPassword(password);
				spettatore.setNome(nome);
				spettatore.setCognome(cognome);
				spettatore.setEta(eta);
				utentiAggiunti.add(spettatore);

				return spettatore;
			}

			throw new InvalidPasswordException();
		}

		throw new IntegerFormatException();
	}

	@Override
	public void updateDati(Utente utente, String name, String surname, String username, Integer age, String oldPassword,
			String newPassword, String repeatPassword) throws BusinessException {
		if (utente.getPassword().equals(oldPassword)) {
			if (newPassword.equals(repeatPassword)) {
				if (age > 0 && age < 100) {
					utente.setEta(age);
					if (!username.isEmpty())
						utente.setUsername(username);
					if (!name.isEmpty())
						utente.setNome(name);
					if (!surname.isEmpty())
						utente.setCognome(surname);
					if (!newPassword.isEmpty())
						utente.setPassword(newPassword);

					return;
				}

				throw new IntegerFormatException();
			}

			throw new InvalidPasswordException();
		}

		throw new UtenteNotFoundException();
	}

}
