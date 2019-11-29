package com.edmar.apiStarWarsResistence.galaxia.exceptions;

public class GalaxiaException extends RuntimeException {
	private static final long serialVersionUID = 7280609599985873641L;

	public GalaxiaException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public GalaxiaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GalaxiaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public GalaxiaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public GalaxiaException(Throwable cause) {
		super(cause);
	}
}
