package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.LuogoService;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

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
		for (Luogo place : luoghiAggiunti) {
			if (luogo.getId() == place.getId()) {
				luoghiAggiunti.remove(luogo);
				return;
			}
		}
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
	public Luogo findLuogoById(int id) throws BusinessException {
		return luoghiAggiunti.get(id);
	}

	@Override
	public Integer getCapienzaRimanente(Luogo luogo) {
		Integer capienzaRimanente = luogo.getCapienza();
		if (luogo.getTipologiaLuogo().toString().equals("Stadio")) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= sector.getCapienza();
				}
			}
		}
		if (luogo.getTipologiaLuogo().toString().equals("Teatro")) {
			for (Settore sector : settoriAggiunti) {
				if (sector.getLuogo().equals(luogo)) {
					capienzaRimanente -= ((sector.getCapienza() * 10) / 7);
				}
			}
		}

		return capienzaRimanente;
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
		for (Settore sector : settoriAggiunti) {
			if (settore.getId() == sector.getId()) {
				settoriAggiunti.remove(settore);
				return;
			}
		}
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

	@Override
	public Settore findSettoreById(int id) throws BusinessException {
		return settoriAggiunti.get(id);
	}

}
