package it.univaq.disim.oop.roc.domain;

public class Carta extends MetodoDiPagamento {

	private String numero;
	private String intestatario;
	private String Scadenza;

	private Integer cvv;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getScadenza() {
		return Scadenza;
	}

	public void setScadenza(String Scadenza) {
		this.Scadenza = Scadenza;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

}
