package it.univaq.disim.oop.roc.domain;

import java.util.HashSet;
import java.util.Set;

public class Settore {

	private String nome;
	private Integer capienza;
	private Luogo luogo;

	private Set<Tariffa> tariffe = new HashSet<>();

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

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	public Set<Tariffa> getTariffe() {
		return tariffe;
	}

	public void setTariffe(Set<Tariffa> tariffe) {
		this.tariffe = tariffe;
	}

	public String toString() {
		return luogo.getNome() + "," + nome + "," + capienza;
	}
}
