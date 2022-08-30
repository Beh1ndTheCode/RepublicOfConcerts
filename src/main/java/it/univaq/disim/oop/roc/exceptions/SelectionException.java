package it.univaq.disim.oop.roc.exceptions;

@SuppressWarnings("serial")
public class SelectionException extends BusinessException {

	public SelectionException() {

	}

	public SelectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public SelectionException(String message, Throwable cause) {
		super(message, cause);

	}

	public SelectionException(String message) {
		super(message);

	}

	public SelectionException(Throwable cause) {
		super(cause);

	}

}
