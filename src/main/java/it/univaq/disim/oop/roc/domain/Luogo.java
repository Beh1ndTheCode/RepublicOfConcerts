package it.univaq.disim.oop.roc.domain;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.tipi.TipologiaLuogo;

public class Luogo {

	private Integer id;
	private String nome;
	private String citta;
	private Integer capienza;
	private TipologiaLuogo tipologiaLuogo;

	private List<Concerto> concerti = new ArrayList<>();
	private List<Settore> settori = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public List<Concerto> getConcerti() {
		return concerti;
	}

	public void setConcerti(List<Concerto> concerti) {
		this.concerti = concerti;
	}

	public List<Settore> getSettori() {
		return settori;
	}

	public void setSettori(List<Settore> settori) {
		this.settori = settori;
	}

	public TipologiaLuogo getTipologiaLuogo() {
		return tipologiaLuogo;
	}

	public void setTipologiaLuogo(TipologiaLuogo tipologiaLuogo) {
		this.tipologiaLuogo = tipologiaLuogo;
	}

	public String toString() {
		return getTipologiaLuogo().toString() + ", " + getNome() + ", " + getCitta();
	}

}
