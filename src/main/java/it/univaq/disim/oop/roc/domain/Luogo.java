package it.univaq.disim.oop.roc.domain;

public abstract class Luogo {
	
	private Integer capienza;
	private String settore;

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}
}
