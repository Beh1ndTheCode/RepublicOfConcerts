package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Stadio;
import it.univaq.disim.oop.roc.domain.Teatro;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.NumberOutOfBoundsException;

public class RAMLuogoServiceImpl implements LuogoService {

	private static List<Luogo> luoghiAggiunti = new ArrayList<>();
	private static List<Settore> settoriAggiunti = new ArrayList<>();

	private static int idCounterLuoghi = 0;
	private static int idCounterSettori = 0;

	@Override
	public void addLuogo(Luogo luogo) throws BusinessException {
		luogo.setId(idCounterLuoghi++);
		luoghiAggiunti.add(luogo);
	}

	@Override
	public void updateLuogo(Luogo luogo) throws BusinessException {
		for (Luogo place : luoghiAggiunti) {
			if (luogo.getId() == place.getId()) {
				place.setNome(luogo.getNome());
				place.setCitta(luogo.getCitta());
				place.setCapienza(luogo.getCapienza());
			}
		}
	}

	@Override
	public void deleteLuogo(Luogo luogo) throws BusinessException {
		luoghiAggiunti.remove(luogo);
	}

	@Override
	public List<Luogo> findAllLuoghi() throws BusinessException {
		List<Luogo> luoghi = new ArrayList<>();

		for (Luogo place : luoghiAggiunti) {
			luoghi.add(place);
		}

		return luoghi;
	}

	@Override
	public Integer getCapienzaRimanente(Luogo luogo) {
		Integer capienzaRimanente = luogo.getCapienza();
		if (luogo instanceof Stadio) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= sector.getCapienza();
				}
			}
		}
		if (luogo instanceof Teatro) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= ((sector.getCapienza() * 10) / 7);
				}
			}
		}

		return capienzaRimanente;
	}

	@Override
	public void verificaCapienza(Luogo luogo, Integer capienzaSettore) throws BusinessException {
		Integer capienzaRimanente = getCapienzaRimanente(luogo);
		capienzaRimanente -= capienzaSettore;
		if (capienzaRimanente < 0)
			throw new NumberOutOfBoundsException();

		return;
	}

	@Override
	public void verificaCapienza(Luogo luogo, Settore settore, Integer capienzaSettore) throws BusinessException {
		Integer capienzaRimanente = getCapienzaRimanente(luogo);
		capienzaRimanente += settore.getCapienza();
		capienzaRimanente -= capienzaSettore;
		if (capienzaRimanente < 0)
			throw new NumberOutOfBoundsException();

		return;
	}

	@Override
	public void addSettore(Settore settore) throws BusinessException {
		settore.setId(idCounterSettori++);
		settoriAggiunti.add(settore);
	}

	@Override
	public void updateSettore(Settore settore) throws BusinessException {
		for (Settore sector : settoriAggiunti) {
			if (settore.getId() == sector.getId()) {
				sector.setNome(settore.getNome());
				sector.setCapienza(settore.getCapienza());
			}
		}
	}

	public void deleteSettore(Settore settore) throws BusinessException {
		settoriAggiunti.remove(settore);
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

}
