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
						utente.setId(Integer.parseInt(colonne[0]));
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
	public Utente registration(Spettatore spettatore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(UTENTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(UTENTI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(spettatore.getUsername());
				row.append(Utility.SEPARATORE);
				row.append(spettatore.getPassword());
				row.append(Utility.SEPARATORE);
				row.append("spettatore");
				row.append(Utility.SEPARATORE);
				row.append(spettatore.getNome());
				row.append(Utility.SEPARATORE);
				row.append(spettatore.getCognome());
				row.append(Utility.SEPARATORE);
				row.append(spettatore.getEta());
				writer.println(row.toString());
				return spettatore;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateDati(Spettatore spettatore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(UTENTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(UTENTI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == spettatore.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(spettatore.getId());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getUsername());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getPassword());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getNome());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getCognome());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getEta());
						writer.println(row.toString());
					} else {
						writer.println(String.join(Utility.SEPARATORE, righe));
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

}