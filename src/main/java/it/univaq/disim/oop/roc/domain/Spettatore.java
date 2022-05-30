package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Spettatore extends Utente {

	private Integer eta;

	private Set<MetodoDiPagamento> metodoPreferito = new HashSet<>();
	private Set<Recensione> recensioniLasciate = new HashSet<>();
	private Set<Biglietto> bigliettiPrenotati = new HashSet<>();

	public Set<Biglietto> getBigliettiPrenotati() {
		return bigliettiPrenotati;
	}

	public void setBigliettiPrenotati(Set<Biglietto> bigliettiPrenotati) {
		this.bigliettiPrenotati = bigliettiPrenotati;
	}

	public Set<Recensione> getRecensioniLasciate() {
		return recensioniLasciate;
	}

	public void setRecensioniLasciate(Set<Recensione> recensioniLasciate) {
		this.recensioniLasciate = recensioniLasciate;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public Set<MetodoDiPagamento> getMetodoPreferito() {
		return metodoPreferito;
	}

	public void setMetodoPreferito(Set<MetodoDiPagamento> metodoPreferito) {
		this.metodoPreferito = metodoPreferito;
	}

	
}
