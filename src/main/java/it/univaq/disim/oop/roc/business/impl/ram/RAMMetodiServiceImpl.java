package it.univaq.disim.oop.roc.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.roc.business.MetodiService;
import it.univaq.disim.oop.roc.domain.Carta;
import it.univaq.disim.oop.roc.domain.Conto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Utente;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public class RAMMetodiServiceImpl implements MetodiService {

	private static List<MetodoDiPagamento> metodiAggiunti = new ArrayList<>();
	private static int idCounterMetodi = 0;

	@Override
	public void addMetodo(MetodoDiPagamento metodo) throws BusinessException {
		metodo.setId(idCounterMetodi++);
		metodiAggiunti.add(metodo);
	}

	@Override
	public void deleteMetodo(MetodoDiPagamento metodo) throws BusinessException {
		for (MetodoDiPagamento method : metodiAggiunti) {
			if (metodo.getId() == method.getId()) {
				metodiAggiunti.remove(metodo);
				return;
			}
		}
	}

	@Override
	public List<MetodoDiPagamento> findAllMetodi(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> metodiDiPagamento = new ArrayList<>();
		for (MetodoDiPagamento method : metodiAggiunti) {
			if (method.getUtente().equals(utente))
				metodiDiPagamento.add(method);
		}
		return metodiDiPagamento;
	}

	@Override
	public List<MetodoDiPagamento> findAllCarte(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> carte = new ArrayList<>();
		for (MetodoDiPagamento method : metodiAggiunti) {
			if (method.getUtente().equals(utente) && method instanceof Carta)
				carte.add(method);
		}
		return carte;
	}

	@Override
	public List<MetodoDiPagamento> findAllConti(Utente utente) throws BusinessException {
		List<MetodoDiPagamento> conti = new ArrayList<>();
		for (MetodoDiPagamento method : metodiAggiunti) {
			if (method.getUtente().equals(utente) && method instanceof Conto)
				conti.add(method);
		}
		return conti;
	}

}
