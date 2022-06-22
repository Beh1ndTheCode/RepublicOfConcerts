package it.univaq.disim.oop.roc.domain;

public abstract class MetodoDiPagamento {

	private String nome;
	private String nomeTitolare;
	private String cognomeTitolare;

	public String getCognomeTitolare() {
		return cognomeTitolare;
	}

	public void setCognomeTitolare(String cognomeTitolare) {
		this.cognomeTitolare = cognomeTitolare;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeTitolare() {
		return nomeTitolare;
	}

	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
	}

}
