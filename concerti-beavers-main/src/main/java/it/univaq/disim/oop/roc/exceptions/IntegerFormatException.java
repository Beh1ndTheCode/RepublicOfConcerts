package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class IntegerFormatException extends BusinessException {

	public IntegerFormatException() {
	}

	public IntegerFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IntegerFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegerFormatException(String message) {
		super(message);
	}

	public IntegerFormatException(Throwable cause) {
		super(cause);
	}

}
