package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.domain.Tour;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class FileTourServiceImpl implements TourService {

	private String tourFilename;

	public FileTourServiceImpl(String tourFilename) {
		this.tourFilename = tourFilename;
	}

	@Override
	public void addTour(Tour tour) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(tourFilename);
			try (PrintWriter writer = new PrintWriter(new File(tourFilename))) {
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
			FileData fileData = Utility.readAllRows(tourFilename);
			try (PrintWriter writer = new PrintWriter(new File(tourFilename))) {
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
			FileData fileData = Utility.readAllRows(tourFilename);
			try (PrintWriter writer = new PrintWriter(new File(tourFilename))) {
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
	public List<Tour> findAllTours() throws BusinessException {
		List<Tour> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(tourFilename);
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

	@Override
	public Tour findTourById(int id) throws BusinessException {
		Tour result = new Tour();
		try {
			FileData fileData = Utility.readAllRows(tourFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(id);
					result.setArtista(colonne[1]);
					result.setNome(colonne[2]);
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
