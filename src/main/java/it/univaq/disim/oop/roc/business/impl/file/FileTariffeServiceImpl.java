package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileTariffeServiceImpl implements TariffeService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String TARIFFE_FILE_NAME = REPOSITORY_BASE + File.separator + "tariffe.txt";

	@Override
	public void addTariffe(Concerto concerto, Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(TARIFFE_FILE_NAME);
			for (Settore settore : luogo.getSettori()) {
				try (PrintWriter writer = new PrintWriter(new File(TARIFFE_FILE_NAME))) {
					Long contatore = fileData.getContatore();
					writer.println(contatore + 1);
					for (String[] righe : fileData.getRighe()) {
						writer.println(String.join(Utility.SEPARATORE, righe));
					}
					StringBuilder row = new StringBuilder();
					row.append(contatore);
					row.append(Utility.SEPARATORE);
					row.append(concerto.getId());
					row.append(Utility.SEPARATORE);
					row.append(settore.getId());
					row.append(Utility.SEPARATORE);
					row.append("");
					writer.println(row.toString());
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void setTariffa(Tariffa tariffa) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(TARIFFE_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(TARIFFE_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == tariffa.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(tariffa.getId());
						row.append(Utility.SEPARATORE);
						row.append(tariffa.getConcerto().getId());
						row.append(Utility.SEPARATORE);
						row.append(tariffa.getSettore().getId());
						row.append(Utility.SEPARATORE);
						row.append(tariffa.getPrezzoIntero());
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
	public List<Tariffa> findAllTariffe(Concerto concerto) throws BusinessException {
		List<Tariffa> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(TARIFFE_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(concerto.getId().toString())) {
					Tariffa tariffa = new Tariffa();
					tariffa.setId(Integer.parseInt(colonne[0]));
					tariffa.setConcerto(concerto);
					tariffa.setSettore(null);
					tariffa.setPrezzo(Float.parseFloat(colonne[3]));
					result.add(tariffa);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

}
