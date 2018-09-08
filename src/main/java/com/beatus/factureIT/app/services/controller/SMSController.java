package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.model.ProductWithLocationsAndPricesRequest;
import com.beatus.billlive.sendsms.model.SMSConfiguration;
import com.beatus.billlive.sendsms.service.SMSService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_SMS_REQUEST)
public class SMSController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSController.class);

	@Resource(name = "smsService")
    private SMSService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String smsHome(HttpServletRequest request, ModelMap model) {
        return "sms/home";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_ADD_SCREEN_CONFIGURATION,
            method = RequestMethod.GET)
    public String addSMSScreenConfiguration(HttpServletRequest request, ModelMap model) {
        return "sms/request-add";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_ADD_SCREEN_CONFIGURATION,
            method = RequestMethod.POST)
    public String addSMSScreenPOST(HttpServletRequest request, SMSConfiguration smsConfiguration, ModelMap model) throws ClassNotFoundException, SQLException {
    	LOGGER.info("productNameLocAndPrice" + smsConfiguration.getSmsUrl());
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
    	String resp = smsService.addSMSScreenConfiguration(request, smsConfiguration, model, companyId, uid);
        return resp;
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_EDIT_SCREEN_CONFIGURATION,
            method = RequestMethod.POST)
    public String editSMSScreenPOST(HttpServletRequest request, SMSConfiguration smsConfiguration, ModelMap model) throws SQLException {
    	LOGGER.info("productNameLocAndPrice" + smsConfiguration.getSmsUrl());
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
    	String resp = smsService.editSMSScreenConfiguration(request, smsConfiguration, model, companyId, uid);
        return resp;
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SCREEN_GET_CONFIGURATION,
            method = RequestMethod.GET)
    public String getSMSScreenConfiguration(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	
    	smsService.getSMSScreenConfiguration(model, companyId);
        return "sms/request-get";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_SCREEN,
            method = RequestMethod.GET)
    public String getSMSScreen(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	
    	smsService.getSMSScreen(request, model, companyId);
        return "sms/request";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_SCREEN,
            method = RequestMethod.POST)
    public String getSMSScreenPOST(HttpServletRequest request, ProductWithLocationsAndPricesRequest productNameLocAndPrice, ModelMap model) throws ClassNotFoundException, SQLException {
    	LOGGER.info("productNameLocAndPrice" + productNameLocAndPrice.getProductNameLocAndPrice());
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
    	smsService.postSMSScreen(request, productNameLocAndPrice, model, companyId, uid);
        return Constants.REDIRECT + "/sms/sendsmsScreen";
    }
}
