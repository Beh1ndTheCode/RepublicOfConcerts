package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Tour {

	private String nome;
	private String artista;
	private String id;
	private Set<Concerto> concerti = new HashSet<>();

	public Set<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(Set<Concerto> concerti) {
		this.concerti = concerti;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String toString() {
		return nome + ", " + artista;
	}

}
