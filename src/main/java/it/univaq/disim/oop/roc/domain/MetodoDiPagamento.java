package it.univaq.disim.oop.roc.domain;

public abstract class MetodoDiPagamento {

	private String nome;
	private Utente utente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
