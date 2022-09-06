package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;

public class FileConcertoServiceImpl implements ConcertoService {

	private String concertiFilename;
	private LuogoService luogoService;

	public FileConcertoServiceImpl(String concertiFilename, LuogoService luogoService) {
		this.concertiFilename = concertiFilename;
		this.luogoService = luogoService;
	}

	@Override
	public Concerto addConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			try (PrintWriter writer = new PrintWriter(new File(concertiFilename))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(concerto.getArtista());
				row.append(Utility.SEPARATORE);
				row.append(concerto.getLuogo().getId());
				row.append(Utility.SEPARATORE);
				row.append(concerto.getData());
				row.append(Utility.SEPARATORE);
				row.append("Inserisci una scaletta");
				row.append(Utility.SEPARATORE);
				row.append("null");
				row.append(Utility.SEPARATORE);
				row.append("null");
				writer.println(row.toString());
				return concerto;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			try (PrintWriter writer = new PrintWriter(new File(concertiFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == concerto.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(concerto.getId());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getArtista());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getLuogo().getId());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getData());
						row.append(Utility.SEPARATORE);
						row.append(concerto.getScaletta());
						row.append(Utility.SEPARATORE);

						if (concerto.getTipoMetodo().toString().equals("null"))
							row.append(concerto.getTipoMetodo().toString());
						row.append(Utility.SEPARATORE);

						if (concerto.getTour().toString().equals("null")
						/* || !(Integer.parseInt(colonne[6]) == concerto.getTour().getId()) */)
							row.append(concerto.getTour().getId());
						else
							row.append("null");

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
	public void deleteConcerto(Concerto concerto) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			try (PrintWriter writer = new PrintWriter(new File(concertiFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == concerto.getId()) {
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
	public List<Concerto> findAllConcerti() throws BusinessException {
		List<Concerto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			for (String[] colonne : fileData.getRighe()) {
				Concerto concerto = new Concerto();
				concerto.setId(Integer.parseInt(colonne[0]));
				concerto.setArtista(colonne[1]);
				concerto.setData(LocalDate.parse(colonne[3]));
				concerto.setScaletta(colonne[4]);
				if (colonne[5].equals("null"))
					concerto.setTipoMetodo(null);
				else
					concerto.setTipoMetodo(TipoMetodoDiPagamento.valueOf(colonne[5]));

				Luogo luogo = luogoService.findLuogoById(Integer.parseInt(colonne[2]));
				concerto.setLuogo(luogo);
				result.add(concerto);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

	@Override
	public List<Concerto> findConcertiByArtista(String artista) throws BusinessException {
		List<Concerto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].toLowerCase().equals(artista.toLowerCase())) {
					Concerto concerto = new Concerto();
					concerto.setId(Integer.parseInt(colonne[0]));
					concerto.setArtista(artista);
					concerto.setData(LocalDate.parse(colonne[3]));
					concerto.setScaletta(colonne[4]);
					concerto.setTipoMetodo(TipoMetodoDiPagamento.valueOf(colonne[5]));

					Luogo luogo = luogoService.findLuogoById(Integer.parseInt(colonne[2]));
					concerto.setLuogo(luogo);
					result.add(concerto);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public List<Concerto> findConcertiByTour(Tour tour) throws BusinessException {
		List<Concerto> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[6]) == tour.getId()) {
					Concerto concerto = new Concerto();
					concerto.setId(Integer.parseInt(colonne[0]));
					concerto.setArtista(colonne[1]);
					concerto.setData(LocalDate.parse(colonne[3]));
					concerto.setScaletta(colonne[4]);
					concerto.setTipoMetodo(TipoMetodoDiPagamento.valueOf(colonne[5]));
					concerto.setTour(tour);

					Luogo luogo = luogoService.findLuogoById(Integer.parseInt(colonne[2]));
					concerto.setLuogo(luogo);
					result.add(concerto);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return result;
	}

	@Override
	public Concerto findConcertoById(int id) throws BusinessException {
		Concerto result = new Concerto();
		try {
			FileData fileData = Utility.readAllRows(concertiFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(Integer.parseInt(colonne[0]));
					result.setArtista(colonne[1]);
					result.setData(LocalDate.parse(colonne[3]));
					result.setScaletta(colonne[4]);
					result.setTipoMetodo(TipoMetodoDiPagamento.valueOf(colonne[5]));

					Luogo luogo = luogoService.findLuogoById(Integer.parseInt(colonne[2]));
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

}