package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concerto {

	private Integer prezzo;
	private String scaletta;

	private MetodoDiPagamento metodo;
	private Tour tour;
	private Luogo luogo;
	private LocalDate dataConcerto;

	private Set<Recensione> recensione = new HashSet<>();
	private Set<Artista> artista = new HashSet<>();

	public Set<Artista> getArtista() {
		return artista;
	}

	public void setArtista(Set<Artista> artista) {
		this.artista = artista;
	}

	public Set<Recensione> getRecensione() {
		return recensione;
	}

	public void setRecensione(Set<Recensione> recensione) {
		this.recensione = recensione;
	}

	public MetodoDiPagamento getMetodo() {
		return metodo;
	}

	public LocalDate getDataConcerto() {
		return dataConcerto;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	public void setDataConcerto(LocalDate dataConcerto) {
		this.dataConcerto = dataConcerto;
	}

	public void setMetodo(MetodoDiPagamento metodo) {
		this.metodo = metodo;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public String getScaletta() {
		return scaletta;
	}

	public void setScaletta(String scaletta) {
		this.scaletta = scaletta;
	}
}
