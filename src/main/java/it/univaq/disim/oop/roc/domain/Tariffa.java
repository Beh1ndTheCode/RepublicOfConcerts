package it.univaq.disim.oop.roc.domain;

public class Tariffa {

	private Integer prezzo;
	private Concerto concerto;
	private Settore settore;

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Concerto getConcerto() {
		return concerto;
	}

	public void setConcerto(Concerto concerto) {
		this.concerto = concerto;
	}

	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}
}
