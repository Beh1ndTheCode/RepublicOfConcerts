package it.univaq.disim.oop.roc.business.impl.ram;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.business.UtenteService;

public class RAMRocBusinessFactoryImpl extends RocBusinessFactory {

	private MetodiService metodiService;
	private TourService tourService;
	private LuogoService luogoService;
	private UtenteService utenteService;
	private ConcertoService concertoService;
	private BigliettoService bigliettoService;
	private RecensioniService recensioniService;
	private TariffeService tariffeService;

	public RAMRocBusinessFactoryImpl() {
		metodiService = new RAMMetodiServiceImpl();
		tourService = new RAMTourServiceImpl();
		luogoService = new RAMLuogoServiceImpl();
		utenteService = new RAMUtenteServiceImpl();
		concertoService = new RAMConcertoServiceImpl();
		bigliettoService = new RAMBigliettoServiceImpl();
		recensioniService = new RAMRecensioniServiceImpl();
		tariffeService = new RAMTariffeServiceImpl();
	}

	@Override
	public MetodiService getMetodiService() {
		return metodiService;
	}

	@Override
	public TourService getTourService() {
		return tourService;
	}

	@Override
	public LuogoService getLuogoService() {
		return luogoService;
	}

	@Override
	public UtenteService getUtenteService() {
		return utenteService;
	}

	@Override
	public ConcertoService getConcertoService() {
		return concertoService;
	}

	@Override
	public BigliettoService getBigliettoService() {
		return bigliettoService;
	}

	@Override
	public RecensioniService getRecensioniService() {
		return recensioniService;
	}

	@Override
	public TariffeService getTariffeService() {
		return tariffeService;
	}

}
