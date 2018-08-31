package com.beatus.factureIT.app.services.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.beatus.factureIT.app.services.controller.LoginController;
import com.beatus.factureIT.app.services.encryption.EncryptionFactory;
import com.beatus.factureIT.app.services.encryption.HashFactory;
import com.beatus.factureIT.app.services.encryption.HashFactory.Hash;
import com.beatus.factureIT.app.services.encryption.KeyChainEntries;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.repository.LoginRepository;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.CookieManager;
import com.beatus.factureIT.app.services.utils.Utils;
import com.beatus.factureIT.authorization.api.FactureITAuthorities;
import com.beatus.factureIT.authorization.api.FactureITUserRoles;
import com.beatus.factureIT.authorization.api.FactureITUserType;

@Component("loginService")
public class LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Resource(name = "loginRepository")
	LoginRepository loginRepository;

	@Resource(name = "keyChainEntries")
	private KeyChainEntries keyChainEntries;

	@Resource(name = "cookieManager")
	private CookieManager cookieManager;

	private static String COOKIE_NAME = "BL_SE";

	private static final byte[] KEY = { (byte) 0x1C, (byte) 0x33, (byte) 0x18, (byte) 0x63, (byte) 0xC8, (byte) 0xA4,
			(byte) 0x3F, (byte) 0xD2, (byte) 0x30, (byte) 0x08, (byte) 0x0F, (byte) 0xC7, (byte) 0xA4, (byte) 0xB0,
			(byte) 0x48, (byte) 0x26 };

	public String checkLogin(User user, HttpServletRequest request, HttpServletResponse response) {
		String loginResp;
		try {
			User dbUser = loginRepository.getUserByUsername(user.getUsername());
			if (dbUser != null && StringUtils.isNotBlank(dbUser.getPassword())) {
				byte[] encBytes = Base64.decodeBase64(dbUser.getPassword());
				byte[] encryptionKey = keyChainEntries.getAesKeyBytes();
				SecretKeySpec encKey = new SecretKeySpec(encryptionKey, Constants.AES);
				EncryptionFactory.Encryption enc = EncryptionFactory.getInstance(encKey.getAlgorithm());
				byte[] idBytes = enc.decrypt(encKey.getEncoded(), encBytes);
				String decryptedPassword = new String(idBytes, Constants.CHAR_SET);

				byte[] hashedPasswordDB = Base64.decodeBase64(decryptedPassword);
				Hash passwordHash = HashFactory.getInstance(Constants.HMACSHA256);
				boolean isPasswordMatch = passwordHash.match(KEY, user.getPassword().getBytes(), hashedPasswordDB);
				if (isPasswordMatch) {
					return Constants.AUTHENTICATED;
				} else {
					return Constants.ERROR_LOGIN;
				}
			} else {
				return Constants.ERROR_LOGIN;
			}
		} catch (ClassNotFoundException | UnsupportedEncodingException | SQLException e) {
			loginResp = Constants.ERROR_LOGIN;
		}
		return loginResp;
	}
	
	public User getUserByUsername(String username) {
		User user= null;
		try {
			user = loginRepository.getUserByUsername(user.getUsername());
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Error getting user {}", username);
			return null;
		}
		return user;
	}

	public UserCreatedResponse addUserProfile(User user){
		String userCreatedResp;
		UserCreatedResponse resp = new UserCreatedResponse();
		try {
			userCreatedResp = loginRepository.addUserProfile(user);
			user.setPassword(null);
			resp.setUser(user);
			resp.setResponse(userCreatedResp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			userCreatedResp = Constants.ERROR_USER_CREATION;
		}
		return resp;
	}

	public UserCreatedResponse createUser(User user) {
		LOGGER.info("User : " + user.toString());
		String userCreatedResp;
		UserCreatedResponse resp = new UserCreatedResponse();
		try {
			if (StringUtils.isBlank(user.getUsername())) {
				user.setUsername(user.getPhone());
			}
			user.setIsVerified("No");
			if (StringUtils.isNotBlank(user.getPhone())) {
				User userfromDb = loginRepository.getUserByUsername(user.getUsername());
				LOGGER.info("userfromDb : " + userfromDb);

				if (userfromDb != null && userfromDb.getUsername() != null) {
					resp.setResponse(Constants.ERROR_USER_WITH_SAME_PHONE_EXISTS);
					return resp;
				}
			}
			String companyId;
			Hash passwordHash = HashFactory.getInstance(Constants.HMACSHA256);
			byte[] hashedPassword = passwordHash.hash(KEY, user.getPassword().getBytes());
			String macString = Base64.encodeBase64URLSafeString(hashedPassword);

			// Encrypt the password
			byte[] encryptionKey = keyChainEntries.getAesKeyBytes();
			SecretKeySpec secretKey = new SecretKeySpec(encryptionKey, Constants.AES);
			EncryptionFactory.Encryption enc = EncryptionFactory.getInstance(secretKey.getAlgorithm());
			byte[] encBytes = enc.encrypt(secretKey.getEncoded(), macString.getBytes(Constants.CHAR_SET));
			String encPasswordStringWithHash = Base64.encodeBase64URLSafeString(encBytes);

			user.setPassword(encPasswordStringWithHash);
			if (StringUtils.isBlank(user.getUid())) {
				user.setUid(Utils.generateRandomKey(50));
			}
			/*
			 * if (StringUtils.isBlank(user.getUserType())) {
			 * user.setUserType(Constants.USER_ADMIN); }
			 */
			if (StringUtils.isBlank(user.getCompanyId())) {
				companyId = Utils.generateRandomKey(50);
				user.setCompanyId(companyId);
			}
			userCreatedResp = loginRepository.addUser(user);
			user.setPassword(null);
			resp.setUser(user);
		} catch (ClassNotFoundException | UnsupportedEncodingException | SQLException e) {
			e.printStackTrace();
			userCreatedResp = Constants.ERROR_USER_CREATION;
		}
		resp.setResponse(userCreatedResp);
		return resp;
	}

	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		cookieManager.addCookie(response, COOKIE_NAME, "", false, true);
	}
	
	public LinkedHashSet<String> getUserRoles(final String userType) {
		LinkedHashSet<String> userRoles = new LinkedHashSet<String>();
		if (userType.contains(FactureITUserType.CUSTOMER.getValue())) {
			userRoles.add(Constants.CUSTOMER_TYPE);
		} 
		if (userType.contains(FactureITUserType.DISTRIBUTOR.getValue())) {
			userRoles.add(Constants.DISTRIBUTOR_TYPE);
		}
		if (userType.contains(FactureITUserType.RETAILER.getValue())) {
			userRoles.add(Constants.RETAILER_TYPE);
		}
		if (userType.contains(FactureITUserType.MANUFACTURER.getValue())) {
			userRoles.add(Constants.MANUFACTURER_TYPE);
		}
		if (userType.contains(FactureITUserType.COLLECTION_AGENT.getValue())) {
			userRoles.add(Constants.COLLECTION_AGENT_TYPE);
		}
		return userRoles;

	}

	public Set<SimpleGrantedAuthority> getUserAuthorities(final String userType) {
		final Set<SimpleGrantedAuthority> authorities = new LinkedHashSet<SimpleGrantedAuthority>();
		if (userType.contains(FactureITUserType.CUSTOMER.getValue())) {
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_CUSTOMER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.WRITE_CUSTOMER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_RETAILER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_USER.getValue()));
		} 
		if (userType.contains(FactureITUserType.DISTRIBUTOR.getValue())) {
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_DISTRIBUTOR.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_MANUFACTURER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.WRITE_DISTRIBUTOR.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_RETAILER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_OR_UPDATE_COLLECTION_AGENT.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_USER.getValue()));
		}
		if (userType.contains(FactureITUserType.RETAILER.getValue())) {
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_DISTRIBUTOR.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_RETAILER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.WRITE_RETAILER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_OR_UPDATE_COLLECTION_AGENT.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_USER.getValue()));
		}
		if (userType.contains(FactureITUserType.MANUFACTURER.getValue())) {
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_DISTRIBUTOR.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_MANUFACTURER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.WRITE_MANUFACTURER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.READ_RETAILER.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.ADD_OR_UPDATE_COLLECTION_AGENT.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_USER.getValue()));
		}
		if (userType.contains(FactureITUserType.COLLECTION_AGENT.getValue())) {
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_COLLECTION_AGENT_ROUTE.getValue()));
			authorities.add(new SimpleGrantedAuthority(FactureITAuthorities.UPDATE_USER.getValue()));
		}
		return authorities;

	}

}
