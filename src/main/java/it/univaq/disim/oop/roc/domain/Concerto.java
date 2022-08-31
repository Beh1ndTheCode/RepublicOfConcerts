package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.Utility;
import it.univaq.disim.oop.roc.tipi.TipoDiMetodoDiPagamento;

public class Concerto {

	private String scaletta;
	private LocalDate data;
	private String artista;//possono essere anche più di uno, è una stringa descrittiva

	private Luogo luogo;
	private TipoDiMetodoDiPagamento metodo;
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

	public TipoDiMetodoDiPagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(TipoDiMetodoDiPagamento metodo) {
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

}
