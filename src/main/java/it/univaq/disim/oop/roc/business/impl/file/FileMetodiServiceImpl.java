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
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileMetodiServiceImpl implements MetodiService {

	private String metodiFilename;

	public FileMetodiServiceImpl(String metodiFilename) {
		this.metodiFilename = metodiFilename;
	}

	@Override
	public void addMetodo(MetodoDiPagamento metodo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(metodiFilename);
			try (PrintWriter writer = new PrintWriter(new File(metodiFilename))) {
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
					row.append(conto.getIntestatario());
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
			FileData fileData = Utility.readAllRows(metodiFilename);
			try (PrintWriter writer = new PrintWriter(new File(metodiFilename))) {
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
			FileData fileData = Utility.readAllRows(metodiFilename);
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
						conto.setIntestatario(colonne[5]);
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
			FileData fileData = Utility.readAllRows(metodiFilename);
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
			FileData fileData = Utility.readAllRows(metodiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(utente.getId().toString())) {
					if (colonne[2].equals("Conto")) {
						Conto conto = new Conto();
						conto.setId(Integer.parseInt(colonne[0]));
						conto.setUtente(utente);
						conto.setNome(colonne[3]);
						conto.setIban(colonne[4]);
						conto.setIntestatario(colonne[5]);
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
	public MetodoDiPagamento findMetodoPreferito(Spettatore spettatore) throws BusinessException {
		MetodoDiPagamento result = null;
		try {
			FileData fileData = Utility.readAllRows(metodiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[1]) == spettatore.getId()) {
					if (Integer.parseInt(colonne[0]) == spettatore.getMetodoPreferito().getId()) {
						result.setId(Integer.parseInt(colonne[0]));
						result.setUtente(spettatore);
						result.setNome(colonne[3]);
						result.setIntestatario(colonne[5]);
						if (colonne[2].equals("Carta")) {
							((Carta) result).setNumero(Long.parseLong(colonne[4]));
							((Carta) result).setScadenza(LocalDate.parse(colonne[6]));
							((Carta) result).setCvv(Integer.parseInt(colonne[7]));
						}
						if (colonne[2].equals("Conto")) {
							((Conto) result).setIban(colonne[4]);
						}
						return result;
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
