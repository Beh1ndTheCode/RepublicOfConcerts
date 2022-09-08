package it.univaq.disim.oop.roc.business.impl.file;

import java.io.File;

import it.univaq.disim.oop.roc.business.BigliettoService;
import it.univaq.disim.oop.roc.business.ConcertoService;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.business.RecensioniService;
import it.univaq.disim.oop.roc.business.RocBusinessFactory;
import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.business.TourService;
import it.univaq.disim.oop.roc.business.UtenteService;

public class FileRocBusinessFactoryImpl extends RocBusinessFactory {

	private UtenteService utenteService;
	private LuogoService luogoService;
	private ConcertoService concertoService;
	private MetodiService metodiService;
	private BigliettoService bigliettoService;
	private RecensioniService recensioniService;
	private TariffeService tariffeService;
	private TourService tourService;

	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String BIGLIETTI_FILE_NAME = REPOSITORY_BASE + File.separator + "biglietti.txt";
	private static final String CONCERTI_FILE_NAME = REPOSITORY_BASE + File.separator + "concerti.txt";
	private static final String LUOGHI_FILE_NAME = REPOSITORY_BASE + File.separator + "luoghi.txt";
	private static final String SETTORI_FILE_NAME = REPOSITORY_BASE + File.separator + "settori.txt";
	private static final String METODI_FILE_NAME = REPOSITORY_BASE + File.separator + "metodi.txt";
	private static final String RECENSIONI_FILE_NAME = REPOSITORY_BASE + File.separator + "recensioni.txt";
	private static final String TARIFFE_FILE_NAME = REPOSITORY_BASE + File.separator + "tariffe.txt";
	private static final String TOUR_FILE_NAME = REPOSITORY_BASE + File.separator + "tour.txt";
	private static final String UTENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "utenti.txt";

	public FileRocBusinessFactoryImpl() {
		utenteService = new FileUtenteServiceImpl(UTENTI_FILE_NAME, metodiService);
		luogoService = new FileLuogoServiceImpl(LUOGHI_FILE_NAME, SETTORI_FILE_NAME);
		concertoService = new FileConcertoServiceImpl(CONCERTI_FILE_NAME, luogoService, tourService);
		tourService = new FileTourServiceImpl(TOUR_FILE_NAME);
		metodiService = new FileMetodiServiceImpl(METODI_FILE_NAME);
		bigliettoService = new FileBigliettoServiceImpl(BIGLIETTI_FILE_NAME, concertoService, luogoService,
				utenteService);
		recensioniService = new FileRecensioniServiceImpl(RECENSIONI_FILE_NAME, utenteService, concertoService);
		tariffeService = new FileTariffeServiceImpl(TARIFFE_FILE_NAME, concertoService, luogoService);
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
