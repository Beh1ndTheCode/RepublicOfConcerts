package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Tour {

	private Artista artista;
	private Set<Concerto> concerti = new HashSet<>();

	public Set<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(Set<Concerto> concerti) {
		this.concerti = concerti;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

}
