package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class EtaFormatException extends BusinessException {

	public EtaFormatException() {
	}

	public EtaFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EtaFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public EtaFormatException(String message) {
		super(message);
	}

	public EtaFormatException(Throwable cause) {
		super(cause);
	}

}
