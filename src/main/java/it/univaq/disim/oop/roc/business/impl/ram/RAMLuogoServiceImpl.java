package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> settoriAggiunti = new ArrayList<>();

	private static int idCounterLuoghi = 1;

	@Override
	public void addLuogo(String tipo, String nome, String citta, Integer capienza) {
		if ("teatro".equals(tipo)) {
			Teatro teatro = new Teatro();
			teatro.setId(idCounterLuoghi++);
			teatro.setNome(nome);
			teatro.setCitta(citta);
			teatro.setCapienza(capienza);
			luoghiAggiunti.add(teatro);
		}

		if ("stadio".equals(tipo)) {
			Stadio stadio = new Stadio();
			stadio.setId(idCounterLuoghi++);
			stadio.setNome(nome);
			stadio.setCitta(citta);
			stadio.setCapienza(capienza);
			luoghiAggiunti.add(stadio);
		}

		return;
	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> luoghi = new ArrayList<>();

		Teatro teatroProva = new Teatro();
		teatroProva.setId(idCounterLuoghi++);
		teatroProva.setTipo("Teatro");
		teatroProva.setNome("Teatro prova");
		teatroProva.setCitta("Pettino");
		teatroProva.setCapienza(150);
		luoghi.add(teatroProva);

		for (Luogo place : luoghiAggiunti) {
			luoghi.add(place);
		}

		return luoghi;
	}

	@Override
	public void addSettore(String nome, Integer capienza, Luogo luogo) throws BusinessException {
		Settore settore = new Settore();
		settore.setNome(nome);
		settore.setLuogo(luogo);
		settore.setCapienza(capienza);
		settoriAggiunti.add(settore);

		return;
	}

	@Override
	public void updateSettore(Settore settore, String nome, Integer capienza) {
		settore.setNome(nome);
		settore.setCapienza(capienza);
	}

}
