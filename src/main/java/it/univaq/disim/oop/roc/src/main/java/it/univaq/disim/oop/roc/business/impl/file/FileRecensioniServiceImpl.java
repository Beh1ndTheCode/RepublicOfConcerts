package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileRecensioniServiceImpl implements RecensioniService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String RECENSIONI_FILE_NAME = REPOSITORY_BASE + File.separator + "recensioni.txt";

	@Override
	public void addRecensione(Spettatore spettatore, Concerto concerto, String titolo, String descrizione,
			Integer valutazione) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(RECENSIONI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(RECENSIONI_FILE_NAME))) {
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
				row.append(concerto);
				row.append(Utility.SEPARATORE);
				row.append(titolo);
				row.append(Utility.SEPARATORE);
				row.append(descrizione);
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateRecensione(Recensione recensione, String titolo, String descrizione, Integer valutazione) {

	}

	@Override
	public void deleteRecensione(Recensione recensione) throws BusinessException {

	}

	@Override
	public void acceptRecensione(Recensione recensione) throws BusinessException {

	}

	@Override
	public void rejectRecensione(Recensione recensione) throws BusinessException {

	}

	@Override
	public List<Recensione> findRecensioniByConcert(Concerto concerto) throws BusinessException {
		return new ArrayList<>();
	}

	@Override
	public List<Recensione> findRecensioniByUser(Spettatore spettatore) throws BusinessException {
		return new ArrayList<>();
	}

	@Override
	public List<Recensione> findRecensioniInAttesa() throws BusinessException {
		return new ArrayList<>();
	}

}
