package com.n26.main.exceptions;

public class WebServicesException extends Exception {

	/**
	 * @description : Customize exception class
	 * @author Pradeep
	 */
	private static final long serialVersionUID = 1L;

	public WebServicesException() {
	}

	public WebServicesException(String message) {
		super(message);
	}

	public WebServicesException(Throwable cause) {
		super(cause);
	}

	public WebServicesException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServicesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
