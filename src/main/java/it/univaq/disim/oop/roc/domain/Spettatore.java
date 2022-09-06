package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Spettatore extends Utente {

	private MetodoDiPagamento metodoPreferito;

	private Set<MetodoDiPagamento> metodiAggiunti = new HashSet<>();
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

	public Set<MetodoDiPagamento> getMetodiAggiunti() {
		return metodiAggiunti;
	}

	public void setMetodiAggiunti(Set<MetodoDiPagamento> metodo) {
		this.metodiAggiunti = metodo;
	}

	public MetodoDiPagamento getMetodoPreferito() {
		return metodoPreferito;
	}

	public void setMetodoPreferito(MetodoDiPagamento metodoPreferito) {
		this.metodoPreferito = metodoPreferito;
	}

}
