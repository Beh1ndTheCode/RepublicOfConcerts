package it.univaq.disim.oop.roc.domain;

public class Bonifico extends MetodoDiPagamento {

	private String iban;
	private String bic;

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}
}