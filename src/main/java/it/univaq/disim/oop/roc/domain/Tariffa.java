package it.univaq.disim.oop.roc.domain;

public class Tariffa {

	private Float prezzo;
	private Integer id;

	private Concerto concerto;
	private Settore settore;

	public Float getPrezzoIntero() {
		return prezzo;
	}
	
	public Float getPrezzoRidotto() {
		return (prezzo * 9) / 10;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public String toString() {
		return settore.getNome() + ", " + prezzo + "€";
	}

}
