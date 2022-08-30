package it.univaq.disim.oop.roc.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.univaq.disim.oop.roc.exceptions.BusinessException;
import it.univaq.disim.oop.roc.exceptions.IntegerFormatException;
import it.univaq.disim.oop.roc.exceptions.InvalidDateException;

public class Utility {

	public static LocalDate VerificaData(String giorno, String mese, String anno) throws BusinessException, InvalidDateException {
		boolean errore = false;
		if (!(giorno.length() == 2 && mese.length() == 2 && anno.length() == 4)) {
			errore = true;
		}
		else {
			Integer giornoInput, meseInput, annoInput;
			
			try {
				giornoInput = Integer.parseInt(giorno);
				meseInput = Integer.parseInt(mese);
				annoInput = Integer.parseInt(anno);
			} catch (NumberFormatException n) {
				throw new IntegerFormatException();
			}
			
			if(giornoInput <= 0 || meseInput <= 0 || annoInput <= 0)
				errore = true;
			else {
				switch (meseInput) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if (giornoInput > 31)
						errore = true;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					if (giornoInput > 30)
						errore = true;
					break;
				case 2:
					if (annoInput%4 == 0) {
						if (giornoInput > 29)
							errore = true;
					}
					else {
						if (giornoInput > 28)
							errore = true;	
					}
					break;			
				}
			}
		}
		if(errore) {
			throw new InvalidDateException();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = giorno + "/" + mese + "/" + anno;
		LocalDate localDate = LocalDate.parse(data, formatter);
		return localDate;	
	}
}
