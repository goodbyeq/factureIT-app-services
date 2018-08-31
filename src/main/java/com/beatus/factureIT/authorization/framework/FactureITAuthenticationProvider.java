package com.beatus.factureIT.authorization.framework;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.service.LoginService;

@Service("authenticationProvider")
public class FactureITAuthenticationProvider implements AuthenticationProvider {

	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		Authentication authenticatedToken = null;
		String principal = (String) authentication.getPrincipal();
		String credentials = (String) authentication.getCredentials();
		UserDetails user = getLoginService().loadUserByUsername((String) authentication.getPrincipal());
		authenticatedToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
				user.getAuthorities());
		return authenticatedToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
