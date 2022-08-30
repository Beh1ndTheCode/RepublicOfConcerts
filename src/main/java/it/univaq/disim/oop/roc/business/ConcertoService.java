package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.MetodoDiPagamento;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;

public interface ConcertoService {

	void addConcerto(String artista, String luogo, String data);

	void updateConcerto(Concerto concerto, String scaletta, MetodoDiPagamento metodo);

	List<Concerto> findAllConcerti() throws BusinessException;

	public Biglietto bookBiglietto(Concerto concerto, Settore settore, Spettatore spettatore);

}
