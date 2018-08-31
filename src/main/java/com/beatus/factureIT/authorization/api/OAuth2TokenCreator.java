package com.beatus.factureIT.authorization.api;

import com.beatus.factureIT.authorization.bo.GoodByeQToken;

public interface OAuth2TokenCreator {

	public GoodByeQToken generateNewToken(final String jwtToken) throws Exception;
}
