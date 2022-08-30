package it.univaq.disim.oop.roc.domain;

public class Conto extends MetodoDiPagamento {

	private String iban;
	
	private String swift;
	
	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

}