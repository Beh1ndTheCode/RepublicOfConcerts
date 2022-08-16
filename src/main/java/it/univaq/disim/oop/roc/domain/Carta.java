package it.univaq.disim.oop.roc.domain;

public class Carta extends MetodoDiPagamento {

	private String numero;
	private Integer meseScadenza;
	private Integer annoScadenza;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getMeseScadenza() {
		return meseScadenza;
	}

	public void setMeseScadenza(Integer meseScadenza) {
		this.meseScadenza = meseScadenza;
	}

	public Integer getAnnoScadenza() {
		return annoScadenza;
	}

	public void setAnnoScadenza(Integer annoScadenza) {
		this.annoScadenza = annoScadenza;
	}

}
