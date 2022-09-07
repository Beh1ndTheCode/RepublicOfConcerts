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

	private UtenteService utenteService;
	private LuogoService luogoService;
	private ConcertoService concertoService;
	private MetodiService metodiService;
	private BigliettoService bigliettoService;
	private RecensioniService recensioniService;
	private TariffeService tariffeService;
	private TourService tourService;

	public RAMRocBusinessFactoryImpl() {
		utenteService = new RAMUtenteServiceImpl();
		luogoService = new RAMLuogoServiceImpl();
		concertoService = new RAMConcertoServiceImpl(tariffeService);
		metodiService = new RAMMetodiServiceImpl();
		bigliettoService = new RAMBigliettoServiceImpl();
		recensioniService = new RAMRecensioniServiceImpl();
		tariffeService = new RAMTariffeServiceImpl();
		tourService = new RAMTourServiceImpl();
	}

	@Override
	public UtenteService getUtenteService() {
		return utenteService;
	}

	@Override
	public LuogoService getLuogoService() {
		return luogoService;
	}

	@Override
	public ConcertoService getConcertoService() {
		return concertoService;
	}

	@Override
	public MetodiService getMetodiService() {
		return metodiService;
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

	@Override
	public TourService getTourService() {
		return tourService;
	}

}
