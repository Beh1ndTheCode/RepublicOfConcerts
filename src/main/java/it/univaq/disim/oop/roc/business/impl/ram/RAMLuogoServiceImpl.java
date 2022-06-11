package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.disim.oop.roc.business.BusinessException;
import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;

public class RAMLuogoServiceImpl implements LuogoService {

	private Set<Luogo> luoghi = new HashSet<>();

	@Override
	public void add(String tipo, String citta, Set<Settore> settori) {
		if ("stadio".equalsIgnoreCase(tipo)) {
			Stadio stadio = new Stadio();
			stadio.setCitta(citta);
			stadio.setSettori(settori);
		}
		if ("teatro".equalsIgnoreCase(tipo)) {
			Teatro teatro = new Teatro();
			teatro.setCitta(citta);
			teatro.setSettori(settori);
		}

	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		return new ArrayList<>(luoghi);
	}

}
