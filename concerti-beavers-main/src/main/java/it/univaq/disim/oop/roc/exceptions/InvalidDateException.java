package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class InvalidDateException extends BusinessException {

	public InvalidDateException() {

	}

	public InvalidDateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidDateException(String message) {
		super(message);

	}

	public InvalidDateException(Throwable cause) {
		super(cause);

	}

}
