package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
	public void review(Recensione recensione, Spettatore spettatore, Concerto concerto, String titolo,
			String descrizione) throws BusinessException {
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

}
