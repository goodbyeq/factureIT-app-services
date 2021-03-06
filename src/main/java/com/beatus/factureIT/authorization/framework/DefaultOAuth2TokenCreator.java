package com.beatus.factureIT.authorization.framework;

import org.springframework.stereotype.Service;

import com.beatus.factureIT.authorization.api.OAuth2TokenCreator;
import com.beatus.factureIT.authorization.bo.FactureITToken;

@Service("tokenCreator")
public class DefaultOAuth2TokenCreator implements OAuth2TokenCreator {

	private static final Integer EXPIRATION_TIME = Integer.valueOf(1800);
	private static final String AUTHORIZATION_BEARER = "Bearer";

	@Override
	public FactureITToken generateNewToken(final String jwtToken) throws Exception {
		FactureITToken token = new FactureITToken();
		token.setAccessToken(jwtToken);
		token.setExpiresIn(EXPIRATION_TIME);
		token.setTokenType(AUTHORIZATION_BEARER);
		return token;
	}

}
