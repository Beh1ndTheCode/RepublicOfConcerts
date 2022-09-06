package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;

public class Concerto {

	private Integer id;
	private String artista;// possono essere anche più di uno, è una stringa descrittiva
	private Luogo luogo;
	private LocalDate data;
	private String scaletta;
	private TipoMetodoDiPagamento tipoMetodo;
	private Tour tour;

	private Set<Tariffa> tariffe = new HashSet<>();
	private Set<Recensione> recensioni = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(Set<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

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

	public TipoMetodoDiPagamento getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(TipoMetodoDiPagamento tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
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

	public Set<Tariffa> getTariffe() {
		return tariffe;
	}

	public void setTariffe(Set<Tariffa> tariffe) {
		this.tariffe = tariffe;
	}

	public String toString() {
		String date = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return luogo.getNome() + ", " + luogo.getCitta() + ", " + date;
	}

}
