package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class Luogo {

	private String citta;
	private Set<Concerto> concerti = new HashSet<>();
	private Set<Settore> settori = new HashSet<>();

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
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

}
