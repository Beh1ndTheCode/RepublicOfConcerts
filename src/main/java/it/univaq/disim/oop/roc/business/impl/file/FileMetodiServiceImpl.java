package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileMetodiServiceImpl implements MetodiService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String METODI_FILE_NAME = REPOSITORY_BASE + File.separator + "metodi.txt";

	@Override
	public void addMetodo(MetodoDiPagamento metodo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(METODI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();

				if (metodo instanceof Carta) {
					Carta carta = (Carta) metodo;
					row.append(contatore);
					row.append(Utility.SEPARATORE);
					row.append(carta.getUtente().getId());
					row.append(Utility.SEPARATORE);
					row.append("Carta");
					row.append(Utility.SEPARATORE);
					row.append(carta.getNome());
					row.append(Utility.SEPARATORE);
					row.append(carta.getNumero());
					row.append(Utility.SEPARATORE);
					row.append(carta.getIntestatario());
					row.append(Utility.SEPARATORE);
					row.append(carta.getScadenza());
					row.append(Utility.SEPARATORE);
					row.append(carta.getCvv());
					writer.println(row.toString());
				}

				if (metodo instanceof Conto) {
					Conto conto = (Conto) metodo;
					row.append(contatore);
					row.append(Utility.SEPARATORE);
					row.append(conto.getUtente().getId());
					row.append(Utility.SEPARATORE);
					row.append("Conto");
					row.append(Utility.SEPARATORE);
					row.append(conto.getNome());
					row.append(Utility.SEPARATORE);
					row.append(conto.getIban());
					row.append(Utility.SEPARATORE);
					row.append(conto.getSwift());
					writer.println(row.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(METODI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == metodo.getId()) {
						StringBuilder row = new StringBuilder();
						row.delete(0, righe.length);
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
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(utente.getId().toString())) {

					if (colonne[2].equals("Carta")) {
						Carta carta = new Carta();
						carta.setId(Integer.parseInt(colonne[0]));
						carta.setUtente(utente);
						carta.setNome(colonne[3]);
						carta.setNumero(Long.parseLong(colonne[4]));
						carta.setIntestatario(colonne[5]);
						carta.setScadenza(LocalDate.parse(colonne[6]));
						carta.setCvv(Integer.parseInt(colonne[7]));
						result.add(carta);
					}

					if (colonne[2].equals("Conto")) {
						Conto conto = new Conto();
						conto.setId(Integer.parseInt(colonne[0]));
						conto.setUtente(utente);
						conto.setNome(colonne[3]);
						conto.setIban(colonne[4]);
						conto.setSwift(colonne[5]);
						result.add(conto);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public List<MetodoDiPagamento> findAllCarte(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(utente.getId().toString())) {
					if (colonne[2].equals("Carta")) {
						Carta carta = new Carta();
						carta.setId(Integer.parseInt(colonne[0]));
						carta.setUtente(utente);
						carta.setNome(colonne[3]);
						carta.setNumero(Long.parseLong(colonne[4]));
						carta.setIntestatario(colonne[5]);
						carta.setScadenza(LocalDate.parse(colonne[6]));
						carta.setCvv(Integer.parseInt(colonne[7]));
						result.add(carta);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public List<MetodoDiPagamento> findAllConti(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(utente.getId().toString())) {
					if (colonne[2].equals("Conto")) {
						Conto conto = new Conto();
						conto.setId(Integer.parseInt(colonne[0]));
						conto.setUtente(utente);
						conto.setNome(colonne[3]);
						conto.setIban(colonne[4]);
						conto.setSwift(colonne[5]);
						result.add(conto);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}
}
