package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileConcertoServiceImpl implements ConcertoService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String CONCERTI_FILE_NAME = REPOSITORY_BASE + File.separator + "concerti.txt";
	private static final String BIGLIETTI_FILE_NAME = REPOSITORY_BASE + File.separator + "biglietti.txt";

	@Override
	public Concerto addConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(CONCERTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(CONCERTI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(concerto.getArtista());
				row.append(Utility.SEPARATORE);
				row.append(concerto.getLuogo().getId());
				row.append(Utility.SEPARATORE);
				row.append(concerto.getData());
				writer.println(row.toString());
				return concerto;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(CONCERTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(CONCERTI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == concerto.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(concerto.getId());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getArtista());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getLuogo().getId());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getData());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getScaletta());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getTipoMetodo());
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
	public void deleteConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(CONCERTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(CONCERTI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == concerto.getId()) {
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
	public List<Concerto> findAllConcerti() throws BusinessException {
		List<Concerto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(CONCERTI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				Concerto concerto = new Concerto();
				concerto.setId(Integer.parseInt(colonne[0]));
				concerto.setArtista(colonne[1]);
				// concerto.getLuogo(colonne[2]);
				concerto.setData(LocalDate.parse(colonne[3]));
				concerto.setScaletta(colonne[4]);
				// concerto.setTipoMetodo(colonne[5]);
				result.add(concerto);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public void prenotaBiglietto(Biglietto biglietto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(BIGLIETTI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(BIGLIETTI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getConcerto().getId());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getSpettatore().getId());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getTariffa().getId());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
