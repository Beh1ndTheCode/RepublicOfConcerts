package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.tipi.TipologiaLuogo;

public class FileLuogoServiceImpl implements LuogoService {

	private String luoghiFilename;
	private String settoriFilename;

	public FileLuogoServiceImpl(String luoghiFilename, String settoriFilename) {
		this.luoghiFilename = luoghiFilename;
		this.settoriFilename = settoriFilename;
	}

	@Override
	public void addLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(luoghiFilename);
			try (PrintWriter writer = new PrintWriter(new File(luoghiFilename))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(luogo.getTipologiaLuogo().toString());
				row.append(Utility.SEPARATORE);
				row.append(luogo.getNome());
				row.append(Utility.SEPARATORE);
				row.append(luogo.getCitta());
				row.append(Utility.SEPARATORE);
				row.append(luogo.getCapienza());
				writer.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(luoghiFilename);
			try (PrintWriter writer = new PrintWriter(new File(luoghiFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == luogo.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(luogo.getId());
						row.append(Utility.SEPARATORE);
						row.append(luogo.getTipologiaLuogo().toString());
						row.append(Utility.SEPARATORE);
						row.append(luogo.getNome());
						row.append(Utility.SEPARATORE);
						row.append(luogo.getCitta());
						row.append(Utility.SEPARATORE);
						row.append(luogo.getCapienza());
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
	public void deleteLuogo(Luogo luogo) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(luoghiFilename);
			try (PrintWriter writer = new PrintWriter(new File(luoghiFilename))) {
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
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(luoghiFilename);
			for (String[] colonne : fileData.getRighe()) {
				Luogo luogo = new Luogo();
				luogo.setId(Integer.parseInt(colonne[0]));
				luogo.setTipologiaLuogo(TipologiaLuogo.valueOf(colonne[1]));
				luogo.setNome(colonne[2]);
				luogo.setCitta(colonne[3]);
				luogo.setCapienza(Integer.parseInt(colonne[4]));
				result.add(luogo);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public Luogo findLuogoById(int id) throws BusinessException {
		Luogo result = new Luogo();
		try {
			FileData fileData = Utility.readAllRows(luoghiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(id);
					result.setTipologiaLuogo(TipologiaLuogo.valueOf(colonne[1]));
					result.setNome(colonne[2]);
					result.setCitta(colonne[3]);
					result.setCapienza(Integer.parseInt(colonne[4]));
					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public Integer getCapienzaRimanente(Luogo luogo) throws BusinessException {
		Integer capienzaRimanente = luogo.getCapienza();
		try {
			FileData fileData = Utility.readAllRows(settoriFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[1]) == luogo.getId()) {
					if (luogo.getTipologiaLuogo().toString().equals("Stadio")) {
						capienzaRimanente -= Integer.parseInt(colonne[3]);
					}
					if (luogo.getTipologiaLuogo().toString().equals("Teatro")) {
						capienzaRimanente -= (Integer.parseInt(colonne[3]) * 10) / 7;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return capienzaRimanente;
	}

	@Override
	public void addSettore(Settore settore) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(settoriFilename);
			try (PrintWriter writer = new PrintWriter(new File(settoriFilename))) {
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
			FileData fileData = Utility.readAllRows(settoriFilename);
			try (PrintWriter writer = new PrintWriter(new File(settoriFilename))) {
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
			FileData fileData = Utility.readAllRows(settoriFilename);
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
	public Settore findSettoreById(int id) throws BusinessException {
		Settore result = new Settore();
		try {
			FileData fileData = Utility.readAllRows(settoriFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(Integer.parseInt(colonne[0]));
					result.setNome(colonne[2]);
					result.setCapienza(Integer.parseInt(colonne[3]));

					Luogo luogo = findLuogoById(Integer.parseInt(colonne[1]));
					result.setLuogo(luogo);

					return result;
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
			FileData fileData = Utility.readAllRows(settoriFilename);
			try (PrintWriter writer = new PrintWriter(new File(settoriFilename))) {
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

}
