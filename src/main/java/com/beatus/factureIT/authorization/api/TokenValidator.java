package com.beatus.factureIT.authorization.api;

import com.beatus.factureIT.app.services.exception.FactureITTokenException;

/**
 * Validates jwt token 
 *
 */
public interface TokenValidator {

	/**
	 * API to validate oauth token
	 * @param requestURI
	 * @param oAuth2TokenString
	 * @return
	 * @throws FactureITTokenException
	 */
	public Boolean validateToken(final String requestURI, final String oAuth2TokenString) throws FactureITTokenException;
}
