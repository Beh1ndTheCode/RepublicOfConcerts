package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class Luogo {

	private Integer id;
	private String tipo;
	private String nome;
	private String citta;
	private Integer capienza;
	private Set<Concerto> concerti = new HashSet<>();
	private Set<Settore> settori = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public Set<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(Set<Concerto> concerti) {
		this.concerti = concerti;
	}

	public Set<Settore> getSettori() {
		return settori;
	}

	public void setSettori(Set<Settore> settori) {
		this.settori = settori;
	}

	@Override
	public String toString() {
		return id + ", " + nome + ", " + citta;
	}

}
