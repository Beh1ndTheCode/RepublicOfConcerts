package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileBigliettoServiceImpl implements BigliettoService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String BIGLIETTI_FILE_NAME = REPOSITORY_BASE + File.separator + "biglietti.txt";

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
				row.append(biglietto.getUtente().getId());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getTariffa().getId());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Biglietto> findAllBiglietti(Utente utente) throws BusinessException {
		List<Biglietto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(BIGLIETTI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[2].equals(utente.getId().toString())) {
					Biglietto biglietto = new Biglietto();
					biglietto.setNumeroBiglietto(Integer.parseInt(colonne[0]));
					biglietto.setUtente(utente);

					ConcertoService concertoService = new FileConcertoServiceImpl();
					Concerto concerto = concertoService.findConcertoById(Integer.parseInt(colonne[2]));
					biglietto.setConcerto(concerto);

					TariffeService tariffeService = new FileTariffeServiceImpl();
					Tariffa tariffa = tariffeService.findTariffaById(Integer.parseInt(colonne[2]));
					biglietto.setTariffa(tariffa);

					result.add(biglietto);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}
}
