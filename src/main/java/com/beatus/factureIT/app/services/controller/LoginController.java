package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.exception.FactureITServiceException;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.service.LoginService;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.CookieManager;
import com.beatus.factureIT.app.services.utils.ManufacturerAppMediaType;

@Controller
@RequestMapping(Constants.WEB_USER_REQUEST)
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "cookieManager")
	private CookieManager cookieManager;

	private static String COOKIE_NAME = "BL_SE";

	public static JSendResponse<UserCreatedResponse> jsend(UserCreatedResponse userCreatedResponse) {
		if (userCreatedResponse == null || userCreatedResponse.getUser() == null) {
			return new JSendResponse<UserCreatedResponse>(Constants.FAILURE, userCreatedResponse);
		} else {
			return new JSendResponse<UserCreatedResponse>(Constants.SUCCESS, userCreatedResponse);
		}
	}

	public static JSendResponse<String> jsend(String response) {
		if (StringUtils.isBlank(response) || Constants.ERROR_LOGIN.equalsIgnoreCase(response) || Constants.FAILURE.equalsIgnoreCase(response)) {
			return new JSendResponse<String>(Constants.FAILURE, response);
		} else {
			return new JSendResponse<String>(Constants.SUCCESS, response);
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET) public String
	 * locationHome(HttpServletRequest request, ModelMap model) {
	 * LOGGER.info("In LoginGet"); return "login/home"; }
	 * 
	 * @RequestMapping(value = Constants.WEB_LOGIN, method = RequestMethod.GET)
	 * public String loginGet(HttpServletRequest request, ModelMap model) { return
	 * "login/request-login"; }
	 */

	@RequestMapping(value = Constants.WEB_LOGIN, method = RequestMethod.POST, consumes = ManufacturerAppMediaType.APPLICATION_JSON, produces = ManufacturerAppMediaType.APPLICATION_JSON)
	public @ResponseBody JSendResponse<String> checkLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user,
			ModelMap model) throws ClassNotFoundException, SQLException {
		LOGGER.info("In checkLogin ");
		String authenticatedResp = loginService.checkLogin(user, request, response);
		LOGGER.info("In checkLogin Resp : " + authenticatedResp);
		return jsend(authenticatedResp);
	}

	/*
	 * @RequestMapping(value = Constants.WEB_USER_SIGNUP, method =
	 * RequestMethod.GET) public String signupGet(HttpServletRequest request,
	 * ModelMap model) { return "login/request-signup"; }
	 * 
	 * @RequestMapping(value = Constants.WEB_LOGOUT, method = RequestMethod.GET)
	 * public String logoutGet(HttpServletRequest request, HttpServletResponse
	 * response, ModelMap model) { loginService.logoutUser(request, response);
	 * return Constants.REDIRECT + "/user/login"; }
	 */

	@RequestMapping(value = Constants.WEB_USER_SIGNUP, method = RequestMethod.POST, consumes = ManufacturerAppMediaType.APPLICATION_JSON, produces = ManufacturerAppMediaType.APPLICATION_JSON)
	public @ResponseBody JSendResponse<UserCreatedResponse> signup(HttpServletRequest request,
			@RequestBody User user, ModelMap model) throws ClassNotFoundException, SQLException {
		LOGGER.info("In signup");
		UserCreatedResponse signupResp = loginService.createUser(user);
		LOGGER.info("In signup Resp : " + signupResp.getResponse());
		return jsend(signupResp);
	}
	
	@RequestMapping(value = Constants.WEB_USER_ADD_PROFILE, method = RequestMethod.POST, consumes = ManufacturerAppMediaType.APPLICATION_JSON, produces = ManufacturerAppMediaType.APPLICATION_JSON)
	public @ResponseBody JSendResponse<UserCreatedResponse> addProfile(HttpServletRequest request,
			@RequestBody User user, ModelMap model) throws ClassNotFoundException, SQLException {
		LOGGER.info("In signup");
		UserCreatedResponse signupResp = loginService.addUserProfile(user);
		LOGGER.info("In signup Resp : " + signupResp.getResponse());
		return jsend(signupResp);
	}
	
	@RequestMapping(value = Constants.WEB_SEND_CODE, method = RequestMethod.GET, consumes = ManufacturerAppMediaType.APPLICATION_JSON, produces = ManufacturerAppMediaType.APPLICATION_JSON)
	public @ResponseBody JSendResponse<String> sendVerifyCode(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String userSendCodeType) throws ClassNotFoundException, SQLException, FactureITServiceException {
		LOGGER.info("In checkLogin ");
		String sendCodeResp = loginService.sendVerifyCode(request, response, username, userSendCodeType);
		LOGGER.info("In checkLogin Resp : " + sendCodeResp);
		return jsend(sendCodeResp);
	}
	
	@RequestMapping(value = Constants.WEB_VERIFY_SEND_CODE, method = RequestMethod.GET, consumes = ManufacturerAppMediaType.APPLICATION_JSON, produces = ManufacturerAppMediaType.APPLICATION_JSON)
	public @ResponseBody JSendResponse<String> verifySendCode(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String code,  @RequestParam String userSendCodeType) throws ClassNotFoundException, SQLException, FactureITServiceException {
		LOGGER.info("In checkLogin ");
		String verifyCodeResp = loginService.verifySendCode(username, code, userSendCodeType);
		LOGGER.info("In checkLogin Resp : " + verifyCodeResp);
		return jsend(verifyCodeResp);
	}
}
