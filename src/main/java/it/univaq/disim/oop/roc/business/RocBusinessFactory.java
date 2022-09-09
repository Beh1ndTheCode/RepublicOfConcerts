package it.univaq.disim.oop.roc.business;

import it.univaq.disim.oop.roc.business.impl.file.FileRocBusinessFactoryImpl;

public abstract class RocBusinessFactory {

	// private static RocBusinessFactory factory = new RAMRocBusinessFactoryImpl();
	private static RocBusinessFactory factory = new FileRocBusinessFactoryImpl();

	public static RocBusinessFactory getInstance() {
		return factory;
	}

	public abstract MetodiService getMetodiService();

	public abstract TourService getTourService();

	public abstract LuogoService getLuogoService();

	public abstract UtenteService getUtenteService();

	public abstract ConcertoService getConcertoService();

	public abstract BigliettoService getBigliettoService();

	public abstract RecensioniService getRecensioniService();

	public abstract TariffeService getTariffeService();

}
