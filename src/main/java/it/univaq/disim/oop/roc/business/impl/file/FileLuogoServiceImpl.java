package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;

public class FileLuogoServiceImpl implements LuogoService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String LUOGHI_FILE_NAME = REPOSITORY_BASE + File.separator + "luoghi.txt";
	private static final String SETTORI_FILE_NAME = REPOSITORY_BASE + File.separator + "settori.txt";

	@Override
	public void addLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(LUOGHI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(LUOGHI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();

				if (luogo instanceof Teatro) {
					row.append(contatore);
					row.append(Utility.SEPARATORE);
					row.append("Teatro");
					row.append(Utility.SEPARATORE);
					row.append(luogo.getNome());
					row.append(Utility.SEPARATORE);
					row.append(luogo.getCitta());
					row.append(Utility.SEPARATORE);
					row.append(luogo.getCapienza());
					writer.println(row.toString());
				}

				if (luogo instanceof Stadio) {
					row.append(contatore);
					row.append(Utility.SEPARATORE);
					row.append("Conto");
					row.append(Utility.SEPARATORE);
					row.append(luogo.getNome());
					row.append(Utility.SEPARATORE);
					row.append(luogo.getCitta());
					row.append(Utility.SEPARATORE);
					row.append(luogo.getCapienza());
					writer.println(row.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(LUOGHI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(LUOGHI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == luogo.getId()) {
						if (luogo instanceof Teatro) {
							StringBuilder row = new StringBuilder();
							row.append(luogo.getId());
							row.append(Utility.SEPARATORE);
							row.append("Teatro");
							row.append(luogo.getNome());
							row.append(Utility.SEPARATORE);
							row.append(luogo.getCitta());
							row.append(Utility.SEPARATORE);
							row.append(luogo.getCapienza());
							writer.println(row.toString());
						}
						if (luogo instanceof Stadio) {
							StringBuilder row = new StringBuilder();
							row.append(luogo.getId());
							row.append(Utility.SEPARATORE);
							row.append("Stadio");
							row.append(Utility.SEPARATORE);
							row.append(luogo.getNome());
							row.append(Utility.SEPARATORE);
							row.append(luogo.getCitta());
							row.append(Utility.SEPARATORE);
							row.append(luogo.getCapienza());
							writer.println(row.toString());
						}
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
	public void deleteLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(LUOGHI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(LUOGHI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == luogo.getId()) {
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
	public void verificaCapienza(Luogo luogo, Integer capienzaSettore)
			throws BusinessException, NumberOutOfBoundsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void verificaCapienza(Luogo luogo, Settore settore, Integer capienzaSettore)
			throws BusinessException, NumberOutOfBoundsException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(LUOGHI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {

				if (colonne[1].equals("Teatro")) {
					Teatro teatro = new Teatro();
					teatro.setId(Integer.parseInt(colonne[0]));
					teatro.setNome(colonne[2]);
					teatro.setCitta(colonne[3]);
					teatro.setCapienza(Integer.parseInt(colonne[4]));
					result.add(teatro);
				}

				if (colonne[1].equals("Stadio")) {
					Stadio stadio = new Stadio();
					stadio.setId(Integer.parseInt(colonne[0]));
					stadio.setNome(colonne[2]);
					stadio.setCitta(colonne[3]);
					stadio.setCapienza(Integer.parseInt(colonne[4]));
					result.add(stadio);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public void addSettore(Settore settore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(SETTORI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(SETTORI_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(settore.getLuogo().getId());
				row.append(Utility.SEPARATORE);
				row.append(settore.getNome());
				row.append(Utility.SEPARATORE);
				row.append(settore.getCapienza());
				writer.println(row.toString());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateSettore(Settore settore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(SETTORI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(SETTORI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == settore.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(settore.getId());
						row.append(Utility.SEPARATORE);
						row.append(settore.getLuogo().getId());
						row.append(Utility.SEPARATORE);
						row.append(settore.getNome());
						row.append(Utility.SEPARATORE);
						row.append(settore.getCapienza());
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
	public List<Settore> findAllSettori(Luogo luogo) throws BusinessException {
		List<Settore> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(SETTORI_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(luogo.getId().toString())) {
					Settore settore = new Settore();
					settore.setId(Integer.parseInt(colonne[0]));
					settore.setLuogo(luogo);
					settore.setNome(colonne[2]);
					settore.setCapienza(Integer.parseInt(colonne[3]));
					result.add(settore);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public void deleteSettore(Settore settore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(SETTORI_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(SETTORI_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == settore.getId()) {
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
	public Integer getCapienzaRimanente(Luogo luogo) {
		// TODO Auto-generated method stub
		return null;
	}

}
