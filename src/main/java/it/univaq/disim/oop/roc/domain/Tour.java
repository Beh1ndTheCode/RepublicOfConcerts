package it.univaq.disim.oop.roc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tour {

	private String nome;
	private String artista;
	private Integer id;
	private List<Concerto> concerti = new ArrayList<>();

	public List<Concerto> getConcerti() {
		return concerti;
	}
	
	public void setConcerti(List<Concerto> concerti) {
		this.concerti = concerti;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return nome + ", " + artista;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

}
