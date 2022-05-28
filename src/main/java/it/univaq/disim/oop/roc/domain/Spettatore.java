package it.univaq.disim.oop.roc.domain;

public class Spettatore extends Utente {

	private Integer eta;
	private MetodoDiPagamento metodo;

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public MetodoDiPagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoDiPagamento metodo) {
		this.metodo = metodo;
	}
}
