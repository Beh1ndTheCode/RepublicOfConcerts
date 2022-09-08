package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.UtenteService;
import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.TipologiaBiglietto;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileBigliettoServiceImpl implements BigliettoService {

	private String bigliettiFilename;
	private ConcertoService concertoService;
	private LuogoService luogoService;
	private UtenteService utenteService;

	public FileBigliettoServiceImpl(String bigliettiFilename, ConcertoService concertoService,
			LuogoService luogoService, UtenteService utenteService) {
		this.bigliettiFilename = bigliettiFilename;
		this.concertoService = concertoService;
		this.luogoService = luogoService;
	}

	@Override
	public void prenotaBiglietto(Biglietto biglietto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(bigliettiFilename);
			try (PrintWriter writer = new PrintWriter(new File(bigliettiFilename))) {
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
				row.append(biglietto.getSettore().getId());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getUtente().getId());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getTipologiaBiglietto().toString());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getPrezzo());
				row.append(Utility.SEPARATORE);
				row.append(biglietto.getPosto());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateBiglietto(Biglietto biglietto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(bigliettiFilename);
			try (PrintWriter writer = new PrintWriter(new File(bigliettiFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == biglietto.getNumeroBiglietto()) {
						StringBuilder row = new StringBuilder();
						row.append(biglietto.getNumeroBiglietto());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getConcerto().getId());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getSettore().getId());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getUtente().getId());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getTipologiaBiglietto());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getPrezzo());
						row.append(Utility.SEPARATORE);
						row.append(biglietto.getPosto());
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
	public List<Biglietto> findAllBiglietti(Utente utente) throws BusinessException {
		List<Biglietto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(bigliettiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[3].equals(utente.getId().toString())) {
					Biglietto biglietto = new Biglietto();
					biglietto.setNumeroBiglietto(Integer.parseInt(colonne[0]));
					biglietto.setTipologiaBiglietto(TipologiaBiglietto.valueOf(colonne[4]));
					biglietto.setUtente(utente);
					biglietto.setPrezzo(Float.parseFloat(colonne[5]));
					biglietto.setPosto(Integer.parseInt(colonne[6]));

					Settore settore = luogoService.findSettoreById(Integer.parseInt(colonne[2]));
					biglietto.setSettore(settore);
					Concerto concerto = concertoService.findConcertoById(Integer.parseInt(colonne[1]));
					biglietto.setConcerto(concerto);

					result.add(biglietto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public List<Biglietto> findBigliettiByConcertoAndUtente(Concerto concerto, Utente utente) throws BusinessException {
		List<Biglietto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(bigliettiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(concerto.getId().toString()) && colonne[3].equals(utente.getId().toString())) {
					Biglietto biglietto = new Biglietto();
					biglietto.setNumeroBiglietto(Integer.parseInt(colonne[0]));
					biglietto.setUtente(utente);
					biglietto.setConcerto(concerto);
					biglietto.setTipologiaBiglietto(TipologiaBiglietto.valueOf(colonne[4]));
					biglietto.setPrezzo(Float.parseFloat(colonne[5]));
					biglietto.setPosto(Integer.parseInt(colonne[6]));

					Settore settore = luogoService.findSettoreById(Integer.parseInt(colonne[2]));
					biglietto.setSettore(settore);

					result.add(biglietto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public List<Biglietto> findBigliettiByConcertoAndSettore(Concerto concerto, Settore settore)
			throws BusinessException {
		List<Biglietto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(bigliettiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(concerto.getId().toString()) && colonne[2].equals(settore.getId().toString())) {
					Biglietto biglietto = new Biglietto();
					biglietto.setNumeroBiglietto(Integer.parseInt(colonne[0]));
					biglietto.setConcerto(concerto);
					biglietto.setSettore(settore);
					biglietto.setTipologiaBiglietto(TipologiaBiglietto.valueOf(colonne[4]));
					biglietto.setPrezzo(Float.parseFloat(colonne[5]));
					biglietto.setPosto(Integer.parseInt(colonne[6]));

					Utente utente = utenteService.findUtenteById(Integer.parseInt(colonne[3]));
					biglietto.setUtente(utente);

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
