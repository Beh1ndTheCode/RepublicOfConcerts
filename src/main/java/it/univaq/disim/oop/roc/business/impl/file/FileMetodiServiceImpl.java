package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
	public Carta addCarta(Carta carta, String nomeCarta, String intestatario, Integer numero, Integer meseScadenza,
			Integer annoScadenza, Integer cvv) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(METODI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(METODI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(carta.getNome());
				row.append(Utility.SEPARATORE);
				row.append(carta.getUtente().getNome());
				row.append(Utility.SEPARATORE);
				row.append(carta.getUtente().getCognome());
				writer.println(row.toString());
				return carta;
			}
		} catch (IOException e) {
			throw new BusinessException(e);
		}

	}

	public Conto addConto(Conto conto, String nomeConto, String iban) throws BusinessException {
		return conto;
	}

	@Override
	// Completare
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {

	}

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		return new ArrayList<>();
	}
}
