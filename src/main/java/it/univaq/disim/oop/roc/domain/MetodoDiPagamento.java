package it.univaq.disim.oop.roc.domain;

import it.univaq.disim.oop.roc.Tipe.TipoDiMetodoDiPagamento;

public abstract class MetodoDiPagamento {

	private Integer id;
	private TipoDiMetodoDiPagamento tipo;
	private String nome;
	private Utente utente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDiMetodoDiPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDiMetodoDiPagamento tipo) {
		this.tipo = tipo;
	}

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
