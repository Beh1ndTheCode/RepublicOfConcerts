package it.univaq.disim.oop.roc.domain;

public class Settore {

	private String nome;
	private Integer capienza;
	private Float tariffa;
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

	public Float getTariffa() {
		return tariffa;
	}

	public void setTariffa(Float tariffa) {
		this.tariffa = tariffa;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}
	
	public String toString() {
		return luogo.getNome() + "," + nome + "," + capienza;
	}
}
