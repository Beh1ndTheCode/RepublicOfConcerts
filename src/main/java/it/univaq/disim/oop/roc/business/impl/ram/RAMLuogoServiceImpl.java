package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> settoriAggiunti = new ArrayList<>();

	private static int idCounterLuoghi = 1;

	@Override
	public void addLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException {
		if (capienza.length() > 0) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}

			if (capienzaInput >= 1) {

				if (luogo instanceof Teatro) {
					luogo.setId(idCounterLuoghi++);
					luogo.setTipo("Teatro");
					luogo.setNome(nome);
					luogo.setCitta(citta);
					luogo.setCapienza(capienzaInput);
					luoghiAggiunti.add(luogo);
				}

				if (luogo instanceof Stadio) {
					luogo.setId(idCounterLuoghi++);
					luogo.setTipo("Stadio");
					luogo.setNome(nome);
					luogo.setCitta(citta);
					luogo.setCapienza(capienzaInput);
					luoghiAggiunti.add(luogo);
				}

				return;
			}
		}

		throw new IntegerFormatException();
	}

	@Override
	public void updateDati(Luogo luogo, String nome, String citta, String capienza) throws BusinessException {
		if (capienza.length() > 0) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}

			luogo.setCapienza(capienzaInput);
			if (!nome.equals(null))
				luogo.setNome(nome);
			if (!citta.equals(null))
				luogo.setCitta(citta);

			return;
		}
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

		return;
	}

	@Override
	public List<Settore> findAllSettori(Luogo luogo) throws BusinessException {
		List<Settore> settori = new ArrayList<>();

		Settore settoreProva = new Settore();
		settoreProva.setNome("Settore prova");
		settoreProva.setCapienza(50);
		settoreProva.setLuogo(luogo);
		settori.add(settoreProva);

		for (Settore sector : settoriAggiunti) {
			if (sector.getLuogo().equals(luogo))
				settori.add(sector);
		}

		return settori;
	}

}
