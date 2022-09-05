package it.univaq.disim.oop.roc.domain;

public class Biglietto {

	private Integer numeroBiglietto;
	private Tariffa tariffa;
	private Integer posto;
	private Concerto concerto;
	private Utente utente;

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

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Tariffa getTariffa() {
		return tariffa;
	}

	public void setTariffa(Tariffa tariffa) {
		this.tariffa = tariffa;
	}

	public Integer getPosto() {
		return posto;
	}

	public void setPosto(Integer posto) {
		this.posto = posto;
	}

}
