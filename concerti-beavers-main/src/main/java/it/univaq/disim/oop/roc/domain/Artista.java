package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Artista {

	private Set<Concerto> concerto = new HashSet<>();
	private Set<Tour> tour = new HashSet<>();

	public Set<Concerto> getConcerto() {
		return concerto;
	}

	public void setConcerto(Set<Concerto> concerto) {
		this.concerto = concerto;
	}

	public Set<Tour> getTour() {
		return tour;
	}

	public void setTour(Set<Tour> tour) {
		this.tour = tour;
	}

}
