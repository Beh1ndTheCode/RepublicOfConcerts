package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Recensione;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileRecensioniServiceImpl implements RecensioniService {

	private String recensioniFilename;
	private UtenteService utenteService;
	private ConcertoService concertoService;

	public FileRecensioniServiceImpl(String recensioniFilename, UtenteService utenteService,
			ConcertoService concertoService) {
		this.recensioniFilename = recensioniFilename;
		this.utenteService = utenteService;
		this.concertoService = concertoService;
	}

	@Override
	public void addRecensione(Recensione recensione) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			try (PrintWriter writer = new PrintWriter(new File(recensioniFilename))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(recensione.getUtente().getId());
				row.append(Utility.SEPARATORE);
				row.append(recensione.getConcerto().getId());
				row.append(Utility.SEPARATORE);
				row.append(recensione.getTitolo());
				row.append(Utility.SEPARATORE);
				row.append(recensione.getDescrizione());
				row.append(Utility.SEPARATORE);
				row.append(recensione.getValutazione());
				row.append(Utility.SEPARATORE);
				row.append(recensione.getApprovato());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateRecensione(Recensione recensione) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			try (PrintWriter writer = new PrintWriter(new File(recensioniFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == recensione.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(recensione.getId());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getUtente().getId());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getConcerto().getId());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getTitolo());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getDescrizione());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getValutazione());
						row.append(Utility.SEPARATORE);
						row.append(recensione.getApprovato());
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
	public void deleteRecensione(Recensione recensione) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			try (PrintWriter writer = new PrintWriter(new File(recensioniFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == recensione.getId()) {
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
	public List<Recensione> findRecensioniByConcerto(Concerto concerto) throws BusinessException {
		List<Recensione> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[2].equals(concerto.getId().toString())) {
					Recensione review = new Recensione();
					review.setId(Integer.parseInt(colonne[0]));
					review.setConcerto(concerto);
					review.setTitolo(colonne[3]);
					review.setDescrizione(colonne[4]);
					review.setValutazione(Integer.parseInt(colonne[5]));
					review.setApprovato(Boolean.parseBoolean(colonne[6]));

					Utente utente = utenteService.findUtenteById(Integer.parseInt(colonne[1]));
					review.setUtente(utente);

					result.add(review);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public List<Recensione> findRecensioniByUtente(Utente utente) throws BusinessException {
		List<Recensione> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(utente.getId().toString())) {
					Recensione review = new Recensione();
					review.setId(Integer.parseInt(colonne[0]));
					review.setUtente(utente);
					review.setTitolo(colonne[3]);
					review.setDescrizione(colonne[4]);
					review.setValutazione(Integer.parseInt(colonne[5]));
					review.setApprovato(Boolean.parseBoolean(colonne[6]));

					Concerto concerto = concertoService.findConcertoById(Integer.parseInt(colonne[2]));
					review.setConcerto(concerto);

					result.add(review);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public List<Recensione> findRecensioniInAttesa() throws BusinessException {
		List<Recensione> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(recensioniFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[6].equals("false")) {
					Recensione review = new Recensione();
					review.setId(Integer.parseInt(colonne[0]));
					review.setTitolo(colonne[3]);
					review.setDescrizione(colonne[4]);
					review.setValutazione(Integer.parseInt(colonne[5]));
					review.setApprovato(Boolean.parseBoolean(colonne[6]));

					Utente utente = utenteService.findUtenteById(Integer.parseInt(colonne[1]));
					review.setUtente(utente);

					Concerto concerto = concertoService.findConcertoById(Integer.parseInt(colonne[2]));
					review.setConcerto(concerto);

					result.add(review);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

}
