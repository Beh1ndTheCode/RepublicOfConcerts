package it.univaq.disim.oop.roc.controller;

import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.viste.ViewDispatcher;

public class ProfiloController implements DataInitializable<Utente>{
	
	private ViewDispatcher dispatcher;
	
	public ProfiloController() {
		dispatcher = ViewDispatcher.getInstance();
	}
	

}
