package it.univaq.disim.oop.roc.domain;

public class Recensione {

	private Integer id;
	private String titolo;
	private String descrizione;
	private Integer valutazione;

	private Spettatore spettatore;
	private Concerto concerto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getValutazione() {
		return valutazione;
	}

	public void setValutazione(Integer valutazione) {
		this.valutazione = valutazione;
	}

	public Spettatore getSpettatore() {
		return spettatore;
	}

	public void setSpettatore(Spettatore spettatore) {
		this.spettatore = spettatore;
	}

	public Concerto getConcerto() {
		return concerto;
	}

	public void setConcerto(Concerto concerto) {
		this.concerto = concerto;
	}

}
