package com.beatus.factureIT.authorization.api;

import com.beatus.factureIT.exception.JceSecureHashException;

public interface SecureHashCheckCommand {

	/**
	 * Check the provided secret against the hash
	 * @param secret the secret to check
	 * @param hash the hash to check it against
	 * @returns whether or not the secret matches the hash
	 * @throws JceSecureHashException if something goes wrong while checking 
	 */
	public boolean check(String secret, String hash) throws JceSecureHashException;
}