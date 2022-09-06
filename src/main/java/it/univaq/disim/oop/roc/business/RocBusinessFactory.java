package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.business.impl.ram.RAMRocBusinessFactoryImpl;

public abstract class RocBusinessFactory {

	private static RocBusinessFactory factory = new RAMRocBusinessFactoryImpl();
	// private static RocBusinessFactory factory = new FileRocBusinessFactoryImpl();

	public static RocBusinessFactory getInstance() {
		return factory;
	}

	public abstract UtenteService getUtenteService();

	public abstract LuogoService getLuogoService();

	public abstract ConcertoService getConcertoService();

	public abstract MetodiService getMetodiService();

	public abstract BigliettoService getBigliettoService();

	public abstract RecensioniService getRecensioniService();

	public abstract TariffeService getTariffeService();

	public abstract TourService getTourService();

}
