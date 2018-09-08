package com.beatus.factureIT.app.services.exception;

public class GoodByeQValidationException extends Exception {

	/**
	 * @author vakey15
	 * This is a exception that is thrown when there is a bad data in the request parameters
	 */
	private static final long serialVersionUID = 1L;

	public GoodByeQValidationException(String message) {
        super(message);
    }

    public GoodByeQValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
