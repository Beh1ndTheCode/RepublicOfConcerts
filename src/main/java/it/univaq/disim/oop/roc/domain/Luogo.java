package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

import it.univaq.disim.oop.roc.tipi.TipologiaLuogo;

public class Luogo {

	private Integer id;
	private String nome;
	private String citta;
	private Integer capienza;
	private TipologiaLuogo tipologiaLuogo;

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

	public TipologiaLuogo getTipologiaLuogo() {
		return tipologiaLuogo;
	}

	public void setTipologiaLuogo(TipologiaLuogo tipologiaLuogo) {
		this.tipologiaLuogo = tipologiaLuogo;
	}

	public String toString() {
		return getTipologiaLuogo().toString() + ", " + getNome() + ", " + getCitta();
	}

}
