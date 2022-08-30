package it.univaq.disim.oop.roc.domain;

import java.time.LocalDate;

public class Carta extends MetodoDiPagamento {

	private Long numero;
	private String intestatario;
	private int meseScadenza, annoScadenza;
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

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	
	public int getmeseScadenza() {
		return meseScadenza;
	}

	public void setmeseScadenza(int mesescadenza) {
		this.meseScadenza = mesescadenza;
	}

	public int getannoScadenza() {
		return annoScadenza;
	}

	public void setannoScadenza(int annoscadenza) {
		this.annoScadenza = annoscadenza;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

}
