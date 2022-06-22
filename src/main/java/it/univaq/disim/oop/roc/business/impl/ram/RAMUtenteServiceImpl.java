package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.HashSet;
import java.util.Set;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.UtenteNotFoundException;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;

public class RAMUtenteServiceImpl implements UtenteService {

	private static Set<Recensione> recensioni = new HashSet<>();

	/*
	 * private static Set<MetodoDiPagamento> metodi = new HashSet<>();
	 */

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
	public Spettatore registration(String username, String password, String nome, String cognome, Integer eta) {
		Spettatore spettatore = new Spettatore();
		spettatore.setUsername(username);
		spettatore.setPassword(password);
		spettatore.setNome(nome);
		spettatore.setCognome(cognome);
		return spettatore;
		// prende in input i valori inseriti dall'utente e crea un oggetto spettatore
	}

	public void review(Concerto concerto, Recensione recensione, String titolo, String descrizione) {
		recensione.setConcerto(concerto);
		recensione.setTitolo(titolo);
		recensione.setDescrizione(descrizione);
		recensioni.add(recensione);
	}

	/*
	 * public void addMetodo(MetodoDiPagamento metodo, String nome, String nomeTitolare, String cognomeTitolare) { 
	 * metodo.setNome(nome);
	 * metodo.setNomeTitolare(nomeTitolare);
	 * metodo.setCognomeTitolare(cognomeTitolare); 
	 * metodi.add(metodo); 
	 * }
	 */

}
