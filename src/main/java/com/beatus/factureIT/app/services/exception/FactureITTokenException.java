package com.beatus.factureIT.app.services.exception;

public class FactureITTokenException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new GBQTokenException.
	 */
	public FactureITTokenException() {
		super();
	}

	/**
	 * Creates a new GBQTokenException that contains the given error
	 * message.
	 *
	 * @param message
	 *            String representation of the error message.
	 */
	public FactureITTokenException(final String message) {
		super(message);
	}

	/**
	 * Creates a new GBQTokenException that wraps the original Throwable
	 * and uses its error message.
	 *
	 * @param cause
	 *            The original Throwable object, if one was thrown.
	 */
	public FactureITTokenException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new GBQTokenException that wraps the original thrown
	 * exception and has the given error message.
	 *
	 * @param message
	 *            String representation of the error message.
	 * @param cause
	 *            The original Throwable object, if one was thrown.
	 */
	public FactureITTokenException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
