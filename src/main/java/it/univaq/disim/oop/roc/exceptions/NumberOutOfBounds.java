package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class NumberOutOfBounds extends BusinessException {

	public NumberOutOfBounds() {
	}

	public NumberOutOfBounds(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NumberOutOfBounds(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberOutOfBounds(String message) {
		super(message);
	}

	public NumberOutOfBounds(Throwable cause) {
		super(cause);
	}

}
