package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;
import it.univaq.disim.oop.roc.exceptions.SelectionException;

public class RAMTariffeServiceImpl implements TariffeService {

	private static List<Tariffa> tariffeAggiunte = new ArrayList<>();

	public void addTariffe(Concerto concerto, Luogo luogo) throws BusinessException {
		for (Settore settore : luogo.getSettori()) {
			Tariffa tariffa = new Tariffa();
			tariffa.setConcerto(concerto);
			tariffa.setSettore(settore);
			settore.getTariffe().add(tariffa);
			concerto.getTariffe().add(tariffa);
			tariffeAggiunte.add(tariffa);
		}
	}

	@Override
	public void setTariffa(Tariffa tariffa, String prezzo) throws BusinessException {
		if(tariffa == null)
			throw new SelectionException();
		Float inputPrezzo;
		try {
			inputPrezzo = Float.parseFloat(prezzo);
		} catch (NumberFormatException n) {
			throw new FloatFormatException();
		}
		if (inputPrezzo < 0)
			throw new FloatFormatException();
		tariffa.setPrezzo(inputPrezzo);
		tariffa.getConcerto().getTariffe().add(tariffa);
		tariffa.getSettore().getTariffe().add(tariffa);

		return;
	}

	@Override
	public List<Tariffa> findTariffeByConcerto(Concerto concerto) throws BusinessException {
		List<Tariffa> tariffe = new ArrayList<>();

		for (Tariffa fee : tariffeAggiunte) {
			if (fee.getConcerto().equals(concerto))
				tariffe.add(fee);
		}

		return tariffe;
	}

}
