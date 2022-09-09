package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Amministratore;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.UtenteNotFoundException;

public class FileUtenteServiceImpl implements UtenteService {

	private String utentiFilename;
	private MetodiService metodiService;

	public FileUtenteServiceImpl(String utentiFilename, MetodiService metodiService) {
		this.utentiFilename = utentiFilename;
		this.metodiService = metodiService;
	}

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		try {
			FileData fileData = Utility.readAllRows(utentiFilename);
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
						utente.setEta(Integer.parseInt(colonne[6]));
					} else {
						throw new BusinessException("errore nella lettura del file");
					}
					if (utente instanceof Spettatore) {
						if (!(colonne[7].equals("null"))) {
							MetodoDiPagamento metodoPreferito = metodiService
									.findMetodoPreferito(Integer.parseInt(colonne[7]));
							((Spettatore) utente).setMetodoPreferito(metodoPreferito);
						} else {
							((Spettatore) utente).setMetodoPreferito(null);
						}
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
			FileData fileData = Utility.readAllRows(utentiFilename);
			try (PrintWriter writer = new PrintWriter(new File(utentiFilename))) {
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
				row.append(Utility.SEPARATORE);
				row.append("null");
				writer.println(row.toString());
				spettatore.setId(Integer.parseInt(contatore.toString()));
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
			FileData fileData = Utility.readAllRows(utentiFilename);
			try (PrintWriter writer = new PrintWriter(new File(utentiFilename))) {
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
						row.append("spettatore");
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getNome());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getCognome());
						row.append(Utility.SEPARATORE);
						row.append(spettatore.getEta());
						row.append(Utility.SEPARATORE);
						if (!(spettatore.getMetodoPreferito() == null))
							row.append(spettatore.getMetodoPreferito().getId());
						else
							row.append("null");
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

	@Override
	public Utente findUtenteById(int id) throws BusinessException {
		Spettatore result = new Spettatore();
		try {
			FileData fileData = Utility.readAllRows(utentiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(Integer.parseInt(colonne[0]));
					result.setUsername(colonne[1]);
					result.setPassword(colonne[2]);
					result.setNome(colonne[4]);
					result.setCognome(colonne[5]);
					result.setEta(Integer.parseInt(colonne[6]));
					if (!(colonne[7].equals("null"))) {
						MetodoDiPagamento metodoPreferito = metodiService
								.findMetodoPreferito(Integer.parseInt(colonne[7]));
						result.setMetodoPreferito(metodoPreferito);
					} else {
						result.setMetodoPreferito(null);
					}

					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

}