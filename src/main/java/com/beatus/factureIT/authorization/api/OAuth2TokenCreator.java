package com.beatus.factureIT.authorization.api;

import com.beatus.factureIT.authorization.bo.FactureITToken;

public interface OAuth2TokenCreator {

	public FactureITToken generateNewToken(final String jwtToken) throws Exception;
}
