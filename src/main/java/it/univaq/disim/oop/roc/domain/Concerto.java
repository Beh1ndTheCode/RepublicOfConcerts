package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concerto {

	private String scaletta;
	private LocalDate data;

	private Luogo luogo;
	private MetodoDiPagamento metodo;
	private Tour tour;

	private Set<String> artisti = new HashSet<>();
	private Set<Recensione> recensioni = new HashSet<>();

	public String getScaletta() {
		return scaletta;
	}

	public void setScaletta(String scaletta) {
		this.scaletta = scaletta;
	}

	public LocalDate getDataConcerto() {
		return data;
	}

	public void setDataConcerto(LocalDate dataConcerto) {
		this.data = dataConcerto;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	public MetodoDiPagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoDiPagamento metodo) {
		this.metodo = metodo;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Set<String> getArtista() {
		return artisti;
	}

	public void setArtista(Set<String> artista) {
		this.artisti = artista;
	}

	public Set<Recensione> getRecensione() {
		return recensioni;
	}

	public void setRecensione(Set<Recensione> recensione) {
		this.recensioni = recensione;
	}

}
