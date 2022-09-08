package it.univaq.disim.oop.roc.domain;

public class Biglietto {

	private Integer numeroBiglietto;
	private Settore settore;
	private Float prezzo;
	private Integer posto;
	private Concerto concerto;
	private Utente utente;
	private TipologiaBiglietto tipologiaBiglietto;
	
	public TipologiaBiglietto getTipologiaBiglietto() {
		return tipologiaBiglietto;
	}

	public void setTipologiaBiglietto(TipologiaBiglietto tipo) {
		this.tipologiaBiglietto = tipo;
	}
	
	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}

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

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getPosto() {
		return posto;
	}

	public void setPosto(Integer posto) {
		this.posto = posto;
	}

}
