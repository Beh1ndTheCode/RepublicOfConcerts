package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMLuogoServiceImpl implements LuogoService {

	private Set<Luogo> luoghi = new HashSet<>();

	private Set<Settore> settori = new HashSet<>();
	
	private Set<Concerto> concerti = new HashSet<>();
	
	private static int idCounterLuoghi = 1;

	@Override
	public void add(String tipo, String citta) {
		if ("stadio".equalsIgnoreCase(tipo)) {
			Stadio stadio = new Stadio();
			stadio.setCitta(citta);
			luoghi.add(stadio);
		}
		if ("teatro".equalsIgnoreCase(tipo)) {
			Teatro teatro = new Teatro();
			teatro.setCitta(citta);
			luoghi.add(teatro);
		}

	}

	public void updateSettore(Settore settore, String nome, Integer capienza) {
		settore.setNome(nome);
		settore.setCapienza(capienza);
		settori.add(settore);
	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> Luoghi = new ArrayList<>(luoghi);
		
		Integer Capienza = 0;
		for (Settore met : settori) {
			Capienza = Capienza + met.getCapienza();
		}
		
		Teatro teatroProva = new Teatro();
		teatroProva.setId(idCounterLuoghi++);
		teatroProva.setTipo("Teatro");
		teatroProva.setNome("Teatro prova");
		teatroProva.setCitta("Pettino");
		teatroProva.setSettori(settori);
		teatroProva.setCapienza(Capienza);
		teatroProva.setConcerti(concerti);
		
		return Luoghi;
	}

}
