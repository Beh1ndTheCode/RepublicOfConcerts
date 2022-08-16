package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class FileUtenteServiceImpl implements UtenteService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String UTENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "utenti.txt";

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		try {
			FileData fileData = Utility.readAllRows(UTENTI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(username) && colonne[2].equals(password)) {
					Utente utente = null;
					switch (colonne[3]) {
					case "amministratore":
						utente = new Amministratore();
						break;
					case "spettatore":
						utente = new Spettatore();
						break;
					default:
						break;
					}
					if (utente != null) {
						utente.setUsername(username);
						utente.setPassword(password);
						utente.setNome(colonne[4]);
						utente.setCognome(colonne[5]);
					} else {
						throw new BusinessException("errore nella lettura del file");
					}

					return utente;
				}
			}
			throw new UtenteNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	// TROVARE IL MODO PER COSTRUIRE L'OGGETTO SPETTATORE E RESTITUIRLO
	public Utente registration(String username, String password, String confermaPassword, String nome, String cognome,
			Integer eta) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(UTENTI_FILE_NAME);

			Spettatore spettatore = new Spettatore();

			try (PrintWriter writer = new PrintWriter(new File(UTENTI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(username);
				row.append(Utility.SEPARATORE);
				row.append(password);
				row.append(Utility.SEPARATORE);
				row.append(nome);
				row.append(Utility.SEPARATORE);
				row.append(cognome);
				row.append(Utility.SEPARATORE);
				row.append(eta);
				writer.println(row.toString());
				return spettatore;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateDati(Utente utente, String oldPassword, String newPassword, String repeatPassword)
			throws BusinessException {

	}

}