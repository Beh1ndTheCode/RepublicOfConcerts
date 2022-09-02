package it.univaq.disim.oop.roc.business;

import java.util.List;

import it.univaq.disim.oop.roc.domain.Biglietto;
import it.univaq.disim.oop.roc.domain.Concerto;
import it.univaq.disim.oop.roc.domain.Luogo;
import it.univaq.disim.oop.roc.domain.Settore;
import it.univaq.disim.oop.roc.domain.Spettatore;
import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.FloatFormatException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;
import it.univaq.disim.oop.roc.tipi.TipoMetodoDiPagamento;

public interface ConcertoService {

	void addConcerto(String artista, Luogo luogo, String giorno, String mese, String anno)
			throws BusinessException, InvalidDateException;

	void updateConcerto(Concerto concerto, String scaletta, String artista, TipoMetodoDiPagamento metodo,
			 String giorno, String mese, String anno, Luogo luogo) throws BusinessException;

	void deleteConcerto(Concerto concerto) throws BusinessException;

	List<Concerto> findAllConcerti() throws BusinessException;
	
	void setTariffa(Settore settore, String tariffa) throws FloatFormatException;

	public Biglietto bookBiglietto(Concerto concerto, Settore settore, Spettatore spettatore);

}
