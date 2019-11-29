package com.edmar.apiStarWarsResistence.rebelde.exceptions;

public class RebeldeException extends RuntimeException {
	
	private static final long serialVersionUID = 7280609599985873641L;

	public RebeldeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RebeldeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RebeldeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RebeldeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RebeldeException(Throwable cause) {
		super(cause);
	}
}
