package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class FloatFormatException extends BusinessException {

	public FloatFormatException() {
	}

	public FloatFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FloatFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FloatFormatException(String message) {
		super(message);
	}

	public FloatFormatException(Throwable cause) {
		super(cause);
	}

}