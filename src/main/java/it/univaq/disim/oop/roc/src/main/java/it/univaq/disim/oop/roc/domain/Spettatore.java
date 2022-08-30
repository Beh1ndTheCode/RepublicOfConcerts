package it.univaq.disim.oop.roc.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Spettatore extends Utente {

	private List<MetodoDiPagamento> metodo = new ArrayList<>();
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

	public List<MetodoDiPagamento> getMetodo() {
		return metodo;
	}

	public void setMetodo(List<MetodoDiPagamento> metodo) {
		this.metodo = metodo;
	}

}
