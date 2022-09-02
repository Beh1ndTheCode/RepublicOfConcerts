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
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> settoriAggiunti = new ArrayList<>();

	private static int idCounterLuoghi = 0;

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
				luogo.setNome(nome);
				luogo.setCitta(citta);
				luogo.setCapienza(capienzaInput);
				getLuoghiAggiunti().add(luogo);
			}

			if (luogo instanceof Stadio) {
				luogo.setId(idCounterLuoghi++);
				luogo.setNome(nome);
				luogo.setCitta(citta);
				luogo.setCapienza(capienzaInput);
				getLuoghiAggiunti().add(luogo);
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
		getLuoghiAggiunti().remove(luogo);

		return;
	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> luoghi = new ArrayList<>();

		for (Luogo place : getLuoghiAggiunti()) {
			luoghi.add(place);
		}

		return luoghi;
	}
	
	public Integer getCapienzaRimanente(Luogo luogo) {
		Integer capienzaRimanente = luogo.getCapienza();
		if(luogo instanceof Stadio) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= sector.getCapienza();
				}
			}
		}
		if(luogo instanceof Teatro) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= ((sector.getCapienza() * 10) / 7);
				}
			}
		}
		
		return capienzaRimanente;
	}

	public void verificaCapienza(Luogo luogo, Integer capienzaSettore) throws BusinessException {
		Integer capienzaRimanente = getCapienzaRimanente(luogo);
		capienzaRimanente -= capienzaSettore;
		if (capienzaRimanente < 0)
			throw new NumberOutOfBoundsException();
		
		return;
	}
	
	public void verificaCapienza(Luogo luogo, Settore settore, Integer capienzaSettore) throws BusinessException {
		Integer capienzaRimanente = getCapienzaRimanente(luogo);
		capienzaRimanente += settore.getCapienza();
		capienzaRimanente -= capienzaSettore;
		if (capienzaRimanente < 0)
			throw new NumberOutOfBoundsException();
		
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
			capienzaInput -= ((capienzaInput * 3) / 10);
			settore.setCapienza(capienzaInput);
			settore.setTariffa(0f);
			settore.setLuogo(luogo);
			settoriAggiunti.add(settore);

			return;
		}

		if (luogo instanceof Stadio) {
			Settore settore = new Settore();
			settore.setNome(nome);
			settore.setCapienza(capienzaInput);
			settore.setTariffa(0f);
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
			verificaCapienza(settore.getLuogo(), settore, capienzaInput);
			settore.setCapienza(capienzaInput);
		}
		if(!nome.isEmpty())
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

		for (Settore sector : settoriAggiunti) {
			if (sector.getLuogo().equals(luogo))
				settori.add(sector);
		}

		return settori;
	}

	public static List<Luogo> getLuoghiAggiunti() {
		return luoghiAggiunti;
	}
}
