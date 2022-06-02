package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Artista {

	private String nome;
	private String descrizione;
	private Set<Concerto> concerti = new HashSet<>();
	private Set<Tour> tour = new HashSet<>();

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Concerto> getConcerto() {
		return concerti;
	}

	public void setConcerto(Set<Concerto> concerto) {
		this.concerti = concerto;
	}

	public Set<Tour> getTour() {
		return tour;
	}

	public void setTour(Set<Tour> tour) {
		this.tour = tour;
	}

}
