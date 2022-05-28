package it.univaq.disim.oop.roc.domain;

public class Concerto {
	
	private Integer prezzo;
	private String scaletta;
	private MetodoDiPagamento metodo;

	public MetodoDiPagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoDiPagamento metodo) {
		this.metodo = metodo;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public String getScaletta() {
		return scaletta;
	}

	public void setScaletta(String scaletta) {
		this.scaletta = scaletta;
	}
}
