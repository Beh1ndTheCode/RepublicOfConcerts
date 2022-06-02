package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concerto {

	private String scaletta;
	private MetodoDiPagamento metodo;
	private Tour tour;
	private Luogo luogo;
	private LocalDate dataConcerto;
	private Set<Recensione> recensioni = new HashSet<>();
	private Set<Artista> artisti = new HashSet<>();
	private Set<Tariffa> tariffe = new HashSet<>();

	public Set<Artista> getArtista() {
		return artisti;
	}

	public void setArtista(Set<Artista> artista) {
		this.artisti = artista;
	}

	public Set<Recensione> getRecensione() {
		return recensioni;
	}

	public void setRecensione(Set<Recensione> recensione) {
		this.recensioni = recensione;
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

	public String getScaletta() {
		return scaletta;
	}

	public void setScaletta(String scaletta) {
		this.scaletta = scaletta;
	}

	public Set<Tariffa> getTariffa() {
		return tariffe;
	}

	public void setTariffa(Set<Tariffa> tariffa) {
		this.tariffe = tariffa;
	}

}
