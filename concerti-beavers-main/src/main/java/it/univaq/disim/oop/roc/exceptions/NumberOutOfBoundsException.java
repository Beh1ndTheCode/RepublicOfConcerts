package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class NumberOutOfBoundsException extends BusinessException {

	public NumberOutOfBoundsException() {
	}

	public NumberOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumberOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberOutOfBoundsException(String message) {
		super(message);
	}

	public NumberOutOfBoundsException(Throwable cause) {
		super(cause);
	}

}
