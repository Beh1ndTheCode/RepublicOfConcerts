package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;

public class RAMTariffeServiceImpl implements TariffeService {

	private static List<Tariffa> tariffeAggiunte = new ArrayList<>();

	@Override
	public void addTariffe(Concerto concerto) throws BusinessException {
		for (Settore settore : concerto.getLuogo().getSettori()) {
			Tariffa tariffa = new Tariffa();
			tariffa.setConcerto(concerto);
			tariffa.setSettore(settore);
			settore.getTariffe().add(tariffa);
			concerto.getTariffe().add(tariffa);
			tariffeAggiunte.add(tariffa);
			System.out.println("Check");
		}
	}

	@Override
	public void setTariffa(Concerto concerto, Settore settore, Tariffa tariffa, String prezzo)
			throws BusinessException {
		Float inputPrezzo;
		try {
			inputPrezzo = Float.parseFloat(prezzo);
		} catch (NumberFormatException n) {
			throw new FloatFormatException();
		}
		if (inputPrezzo < 0)
			throw new FloatFormatException();
		tariffa.setPrezzo(inputPrezzo);
		tariffa.setConcerto(concerto);
		tariffa.setSettore(settore);
		concerto.getTariffe().add(tariffa);
		settore.getTariffe().add(tariffa);

		return;
	}

	@Override
	public List<Tariffa> findTariffeByConcerto(Concerto concerto) throws BusinessException {
		List<Tariffa> tariffe = new ArrayList<>();

		for (Tariffa fee : tariffeAggiunte) {
			System.out.println("Check");

			if (fee.getConcerto().equals(concerto)) {
				tariffe.add(fee);
				System.out.println(fee.toString());
			}
		}

		return tariffe;
	}

}
