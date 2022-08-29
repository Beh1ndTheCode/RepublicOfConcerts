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
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBounds;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> settoriAggiunti = new ArrayList<>();

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

	public void verificaCapienza(Luogo luogo, Integer capienzaSettore) throws BusinessException {
		Integer capienzaRimanente = 0;
		for (Settore sector : settoriAggiunti) {
			if (sector.getLuogo().equals(luogo)) {
				capienzaRimanente += sector.getCapienza();
			}
		}
		capienzaRimanente += capienzaSettore;
		if (capienzaRimanente > luogo.getCapienza())
			throw new NumberOutOfBounds();

		return;
	}

	@Override
	public void addSettore(Luogo luogo, String nome, String capienza) throws BusinessException {
		Integer capienzaInput;

		try {
			capienzaInput = Integer.parseInt(capienza);
		} catch (NumberFormatException n) {
			throw new IntegerFormatException();
		}

		verificaCapienza(luogo, capienzaInput);

		if (luogo instanceof Teatro) {
			Settore settore = new Settore();
			settore.setNome(nome);
			capienzaInput -= ((capienzaInput * 30) / 100);
			settore.setCapienza(capienzaInput);
			settore.setLuogo(luogo);
			settoriAggiunti.add(settore);

			return;
		}

		if (luogo instanceof Stadio) {
			Settore settore = new Settore();
			settore.setNome(nome);
			settore.setCapienza(capienzaInput);
			settore.setLuogo(luogo);
			settoriAggiunti.add(settore);

			return;
		}

		throw new BusinessException();
	}

	@Override
	public void updateSettore(Settore settore, String nome, String capienza) throws BusinessException {
		if (capienza.length() >= 0 && !capienza.isEmpty()) {
			Integer capienzaInput;
			try {
				capienzaInput = Integer.parseInt(capienza);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			settore.setCapienza(capienzaInput);
		}
		settore.setNome(nome);

		return;
	}

	public void deleteSettore(Settore settore) throws BusinessException {
		settoriAggiunti.remove(settore);

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

		for (Settore sector : settoriAggiunti) {
			if (sector.getLuogo().equals(luogo))
				settori.add(sector);
		}

		return settori;
	}

}
