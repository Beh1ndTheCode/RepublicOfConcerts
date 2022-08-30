package it.univaq.disim.oop.roc.domain;

public class Biglietto {

	private Integer numeroBiglietto;
	private Settore settore;
	private Integer posto;
	private Concerto concerto;
	private Spettatore spettatore;

	public Integer getNumeroBiglietto() {
		return numeroBiglietto;
	}

	public void setNumeroBiglietto(Integer numeroBiglietto) {
		this.numeroBiglietto = numeroBiglietto;
	}

	public Concerto getConcerto() {
		return concerto;
	}

	public void setConcerto(Concerto concerto) {
		this.concerto = concerto;
	}

	public Spettatore getSpettatore() {
		return spettatore;
	}

	public void setSpettatore(Spettatore spettatore) {
		this.spettatore = spettatore;
	}

	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}
	
	public Integer getPosto() {
		return posto;
	}

	public void setPosto(Integer posto) {
		this.posto = posto;
	}

}
