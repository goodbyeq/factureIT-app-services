package com.beatus.factureIT.app.services.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.CookieManager;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SessionInterceptor.class);

	private static final String INTERCEPTOR_LOG_START = "{} request to the session interceptor execution start on {} {}.",
			INTERCEPTOR_LOG_END = "The session interceptor execution finish on {} {}, {}.";

	@Resource(name = "cookieManager")
	private CookieManager cookieManager;

	private static String COOKIE_NAME = "BL_SE";

	@PostConstruct
	public void init() {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		/*final String requestMethod = request.getMethod(), servletPath = request.getRequestURI();
		LOG.debug(INTERCEPTOR_LOG_START, "Incoming", requestMethod, servletPath);
		boolean continueExecution = false;
		Map<String, String> cookieContent = cookieManager.getExistingCookie(request, response, COOKIE_NAME);
		boolean isPageValid = cookieContent != null ? true : false;
		if (StringUtils.isNotBlank(servletPath) && !servletPath.contains(Constants.WEB_LOGIN) && !servletPath.contains(Constants.WEB_USER_SIGNUP) && !isPageValid) {
			response.sendRedirect("/billlive-sendsms/user/login");
			return continueExecution;
		} else {
			if (cookieContent != null) {
				request.setAttribute(Constants.USERNAME, cookieContent.get(Constants.USERNAME));
				request.setAttribute(Constants.COMPANY_ID, cookieContent.get(Constants.COMPANY_ID));
				request.setAttribute(Constants.USER_TYPE, cookieContent.get(Constants.USER_TYPE));
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.debug("Exiting interceptor post with session model: {}");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		final String requestMethod = request.getMethod(), servletPath = request.getRequestURI();
		LOG.debug(INTERCEPTOR_LOG_START, "After Completion", requestMethod, servletPath);
		super.afterCompletion(request, response, handler, ex);
		LOG.debug(INTERCEPTOR_LOG_END, "After Completion", requestMethod, servletPath);
	}
}
