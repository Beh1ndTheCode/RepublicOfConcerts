package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.HashSet;
import java.util.Set;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.EtaFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidPasswordException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class RAMUtenteServiceImpl implements UtenteService {

	private static Set<Recensione> recensioni = new HashSet<>();

	private static Set<MetodoDiPagamento> metodi = new HashSet<>();

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

	public void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo,
			String descrizione) throws BusinessException {
		recensione.setSpettatore(spettatore);
		recensione.setConcerto(concerto);
		recensione.setTitolo(titolo);
		recensione.setDescrizione(descrizione);
		recensioni.add(recensione);
	}

	@Override
	public void addConto(String nome, Spettatore spettatore, String iban) throws BusinessException {
		Conto conto = new Conto();
		conto.setNome(nome);
		conto.setUtente(spettatore);
		conto.setIban(iban);
		metodi.add(conto);
	}

	public void addCarta(String nome, Spettatore spettatore, Integer numero, Integer meseScadenza, Integer annoScadenza)
			throws BusinessException {
		Carta carta = new Carta();
		carta.setNome(nome);
		carta.setUtente(spettatore);
		carta.setNumero(numero);
		carta.setMeseScadenza(meseScadenza);
		carta.setAnnoScadenza(annoScadenza);
		metodi.add(carta);
	}

}
