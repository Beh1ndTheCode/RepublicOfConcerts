package it.univaq.disim.oop.roc.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Luogo {

	private Integer id;
	private String nome;
	private String citta;
	private Integer capienza;

	private List<Concerto> concerti = new ArrayList<>();
	private List<Settore> settori = new ArrayList<>();

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

	public List<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(List<Concerto> concerti) {
		this.concerti = concerti;
	}

	public List<Settore> getSettori() {
		return settori;
	}

	public void setSettori(List<Settore> settori) {
		this.settori = settori;
	}
}
