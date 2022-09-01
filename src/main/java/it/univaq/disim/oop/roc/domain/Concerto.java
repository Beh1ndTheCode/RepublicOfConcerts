package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;

public class Concerto {

	private String scaletta;
	private LocalDate data;
	private String artista;// possono essere anche più di uno, è una stringa descrittiva

	private Luogo luogo;
	private TipoMetodoDiPagamento metodo;
	private Tour tour;

	private List<Recensione> recensioni = new ArrayList<>();

	public String getScaletta() {
		return scaletta;
	}

	public void setScaletta(String scaletta) {
		this.scaletta = scaletta;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	public TipoMetodoDiPagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(TipoMetodoDiPagamento metodo) {
		this.metodo = metodo;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public List<Recensione> getRecensione() {
		return recensioni;
	}

	public void setRecensione(List<Recensione> recensione) {
		this.recensioni = recensione;
	}

	public String toString() {
		String date = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return luogo.getNome() + ", " + luogo.getCitta() + ", " + date;
	}

}
