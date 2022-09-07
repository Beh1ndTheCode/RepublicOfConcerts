package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileTariffeServiceImpl implements TariffeService {

	private String tariffeFilename;
	private ConcertoService concertoService;
	private LuogoService luogoService;

	public FileTariffeServiceImpl(String tariffeFilename, ConcertoService concertoService, LuogoService luogoService) {
		this.tariffeFilename = tariffeFilename;
		this.concertoService = concertoService;
		this.luogoService = luogoService;
	}

	@Override
	public void addTariffe(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(tariffeFilename);
			List<Settore> settoriLuogo = luogoService.findAllSettori(concerto.getLuogo());
			for (Settore settore : settoriLuogo) {
				try (PrintWriter writer = new PrintWriter(new File(tariffeFilename))) {
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
					row.append("null");
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
			FileData fileData = Utility.readAllRows(tariffeFilename);
			try (PrintWriter writer = new PrintWriter(new File(tariffeFilename))) {
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
			FileData fileData = Utility.readAllRows(tariffeFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(concerto.getId().toString())) {
					Tariffa tariffa = new Tariffa();
					tariffa.setId(Integer.parseInt(colonne[0]));
					tariffa.setConcerto(concerto);
					if (colonne[3].toString().equals("null"))
						tariffa.setPrezzo(null);
					else
						tariffa.setPrezzo(Float.parseFloat(colonne[3]));

					Settore settore = luogoService.findSettoreById(Integer.parseInt(colonne[2]));
					tariffa.setSettore(settore);
					result.add(tariffa);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public Tariffa findTariffaById(int id) throws BusinessException {
		Tariffa result = new Tariffa();
		try {
			FileData fileData = Utility.readAllRows(tariffeFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(Integer.parseInt(colonne[0]));
					if (colonne[3].toString().equals("null"))
						result.setPrezzo(null);
					else
						result.setPrezzo(Float.parseFloat(colonne[3]));

					Concerto concerto = concertoService.findConcertoById(Integer.parseInt(colonne[1]));
					result.setConcerto(concerto);

					Settore settore = luogoService.findSettoreById(Integer.parseInt(colonne[2]));
					result.setSettore(settore);

					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

}
