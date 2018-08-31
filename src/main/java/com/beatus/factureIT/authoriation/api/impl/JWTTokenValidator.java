package com.beatus.factureIT.authoriation.api.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beatus.factureIT.app.services.exception.FactureITTokenException;
import com.beatus.factureIT.app.services.exception.FactureITUserException;
import com.beatus.factureIT.app.services.exception.JwtTokenMissingException;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.service.LoginService;
import com.beatus.factureIT.authorization.api.SecretService;
import com.beatus.factureIT.authorization.api.TokenValidator;
import com.beatus.factureIT.authorization.framework.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("tokenValidators")
public class JWTTokenValidator implements TokenValidator {

	@Autowired(required = true)
	private JwtSettings jwtSettings;

	@Autowired(required = true)
	private SecretService secretService;

	@Resource(name = "loginService")
	private LoginService loginService;

	public JwtSettings getJwtSettings() {
		return jwtSettings;
	}

	public void setJwtSettings(JwtSettings jwtSettings) {
		this.jwtSettings = jwtSettings;
	}

	public SecretService getSecretService() {
		return secretService;
	}

	public void setSecretService(SecretService secretService) {
		this.secretService = secretService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public Boolean validateToken(String requestURI, String oAuth2TokenString) throws FactureITTokenException {
		Boolean isTokenValid = false;
		String header = oAuth2TokenString;
		try {
			if (header == null || !header.startsWith("Bearer ")) {
				throw new JwtTokenMissingException("No JWT token found in request headers");
			}
			String authToken = header.substring(7);
			SignatureAlgorithm algorithm = getJwtSettings()
					.getSignatureAlgorithm(getJwtSettings().getTokenSigningAlgorithm());
			// Validate signature
			Jws<Claims> claims = Jwts.parser().setSigningKey(getSecretService().getHS256SecretBytes())
					.parseClaimsJws(authToken);

			// Validate issuer
			if (!getJwtSettings().getTokenIssuer().equals(claims.getBody().getIssuer())) {
				throw new FactureITTokenException("Token Issuer did not match");
			}

			// Validate expiration time
			Date expirationDate = claims.getBody().getExpiration();
			long nowMillis = System.currentTimeMillis();
			Timestamp currentTimestamp = new Timestamp(nowMillis);
			if (currentTimestamp.after(expirationDate)) {
				throw new FactureITTokenException("Token expired");
			}

			// Validate user exists in system
			isTokenValid = this.validatePrincipalUser(claims.getBody().getSubject());

		} catch (final Exception e) {
			throw new FactureITTokenException("Exception while validating token", e);
		}
		return isTokenValid;
	}

	private boolean validatePrincipalUser(final String principalID)
			throws JwtTokenMissingException, FactureITTokenException, FactureITUserException {
		User vo = null;
		if (!StringUtils.hasText(principalID)) {
			throw new FactureITTokenException("No JWT subject found in request headers");
		}
		vo = getLoginService().getUserByUsername(principalID);

		if (vo == null) {
			throw new FactureITTokenException("User not found");
		}
		return true;
	}

}
