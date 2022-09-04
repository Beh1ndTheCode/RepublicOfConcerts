package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.TariffeService;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Tariffa;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMTariffeServiceImpl implements TariffeService {

	private static List<Tariffa> tariffeAggiunte = new ArrayList<>();

	private static int idCounterTariffe = 0;

	public void addTariffe(Concerto concerto, Luogo luogo) throws BusinessException {
		for (Settore settore : luogo.getSettori()) {
			Tariffa tariffa = new Tariffa();
			tariffa.setId(idCounterTariffe++);
			tariffa.setConcerto(concerto);
			tariffa.setSettore(settore);
			settore.getTariffe().add(tariffa);
			concerto.getTariffe().add(tariffa);
			tariffeAggiunte.add(tariffa);
		}
	}

	@Override
	public void setTariffa(Tariffa tariffa) throws BusinessException {
		for (Tariffa fee : tariffeAggiunte) {
			if (tariffa.getId() == fee.getId()) {
				fee.setPrezzo(tariffa.getPrezzo());
			}
		}
	}

	@Override
	public List<Tariffa> findAllTariffe(Concerto concerto) throws BusinessException {
		List<Tariffa> tariffe = new ArrayList<>();

		for (Tariffa fee : tariffeAggiunte) {
			if (fee.getConcerto().equals(concerto))
				tariffe.add(fee);
		}

		return tariffe;
	}

}
