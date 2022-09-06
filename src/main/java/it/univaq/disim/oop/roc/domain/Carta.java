package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;

public class Carta extends MetodoDiPagamento {

	private Long numero;
	private LocalDate scadenza;
	private Integer cvv;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String toString() {
		return getNome() + ", " + getIntestatario();
	}

}
