package com.edmar.apiStarWarsResistence.exception;

public class BasicException extends RuntimeException{
	private static final long serialVersionUID = 7280609599985873641L;

	public BasicException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BasicException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BasicException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BasicException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BasicException(Throwable cause) {
		super(cause);
	}
}
