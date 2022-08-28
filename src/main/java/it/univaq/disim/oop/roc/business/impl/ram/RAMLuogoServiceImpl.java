package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> SettoriAggiunti = new ArrayList<>();

	private static int idCounterLuoghi = 1;

	@Override
	public void addLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException {
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

		throw new IntegerFormatException();
	}

	@Override
	public void updateLuogo(Luogo luogo, String nome, String citta, String capienza) throws BusinessException {
		if (capienza.length() > 0) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			luogo.setCapienza(capienzaInput);	
		}
		if (!nome.isEmpty())
			luogo.setNome(nome);
		if (!citta.isEmpty())
			luogo.setCitta(citta);
		
		return;
	}

	@Override
	public void deleteLuogo(Luogo luogo) throws BusinessException {
		luoghiAggiunti.remove(luogo);

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
	public void addSettore(Luogo luogo, String nome, String capienza, String tariffa) throws BusinessException {
		if (capienza.length() >= 0) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			if (tariffa.length() > 0) {
				Float tariffaInput;
				try {
					tariffaInput = Float.parseFloat(tariffa);
				} catch (NumberFormatException n) {
					throw new FloatFormatException();
				}
				Settore settore = new Settore();
				settore.setNome(nome);
				settore.setLuogo(luogo);
				settore.setCapienza(capienzaInput);
				settore.setTariffa(tariffaInput);
				SettoriAggiunti.add(settore);
				
				return;
			}
			throw new FloatFormatException();
		}
		throw new IntegerFormatException();
	}

	@Override
	public void updateSettore(Settore settore, String nome, String capienza, String tariffa) throws BusinessException{
		if (capienza.length() >= 0 && !capienza.isEmpty()) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			settore.setCapienza(capienzaInput);
		}
		if (tariffa.length() > 0) {
			Float tariffaInput;
			try {
				tariffaInput = Float.parseFloat(tariffa);
			} catch (NumberFormatException n) {
				throw new FloatFormatException();
			}
			settore.setTariffa(tariffaInput);
		}
		settore.setNome(nome);
		
		return;
	}
	
	public void deleteSettore(Settore settore) throws BusinessException {
		SettoriAggiunti.remove(settore);

		return;
	}

	@Override
	public List<Settore> findAllSettori(Luogo luogo) throws BusinessException {
		List<Settore> settori = new ArrayList<>();

		Settore settoreProva = new Settore();
		settoreProva.setNome("Settore prova");
		settoreProva.setCapienza(50);
		settoreProva.setTariffa(20.99f);
		settoreProva.setLuogo(luogo);
		settori.add(settoreProva);

		for (Settore sector : SettoriAggiunti) {
			if (sector.getLuogo().equals(luogo))
				settori.add(sector);
		}

		return settori;
	}

}
