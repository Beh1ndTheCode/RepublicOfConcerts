package it.univaq.disim.oop.roc.domain;

public class Settore {

	private String nome;
	private Integer capienza;
	private Integer fila;
	private Integer posto;
	private Luogo luogo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	public Integer getPosto() {
		return posto;
	}

	public void setPosto(Integer posto) {
		this.posto = posto;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

}
