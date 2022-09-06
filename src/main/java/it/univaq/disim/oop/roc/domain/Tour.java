package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Tour {

	private Integer id;
	private String nome;
	private String artista;

	private Set<Concerto> concerti = new HashSet<>();

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Set<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(Set<Concerto> concerti) {
		this.concerti = concerti;
	}

}
