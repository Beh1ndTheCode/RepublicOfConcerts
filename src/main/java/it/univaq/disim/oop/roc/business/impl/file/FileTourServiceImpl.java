package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileTourServiceImpl implements TourService {

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String TOUR_FILE_NAME = REPOSITORY_BASE + File.separator + "tour.txt";

	@Override
	public void addTour(Tour tour) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(TOUR_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(TOUR_FILE_NAME))) {
				Long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE);
				row.append(tour.getArtista());
				row.append(Utility.SEPARATORE);
				row.append(tour.getNome());
				writer.println(row.toString());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateTour(Tour tour) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(TOUR_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(TOUR_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == tour.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(tour.getId());
						row.append(Utility.SEPARATORE);
						row.append(tour.getArtista());
						row.append(Utility.SEPARATORE);
						row.append(tour.getNome());
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
	public void deleteTour(Tour tour) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(TOUR_FILE_NAME);
			try (PrintWriter writer = new PrintWriter(new File(TOUR_FILE_NAME))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == tour.getId()) {
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
	public void addConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteConcerti(Tour tour, List<Concerto> concerti) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tour> findAllTours() throws BusinessException {
		List<Tour> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(TOUR_FILE_NAME);
			for (String[] colonne : fileData.getRighe()) {
				Tour tour = new Tour();
				tour.setId(Integer.parseInt(colonne[0]));
				tour.setArtista(colonne[1]);
				tour.setNome(colonne[2]);
				result.add(tour);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}

}
