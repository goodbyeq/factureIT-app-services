package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.ContactUs;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.service.ContactUsService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_CONTACT_REQUEST)
public class ContactUsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

	@Resource(name = "contactUsService")
	private ContactUsService contactUsService;
	
	public static JSendResponse<String> jsend(String response) {
		if (response != null && response.equals(Constants.CREATED)) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	@RequestMapping(value = Constants.WEB_CONTACT_ADD_REQUEST, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addContactUsInfoPost(HttpServletRequest request,
			ModelMap model, @RequestBody ContactUs contact) throws ClassNotFoundException, SQLException {
		String response = contactUsService.addContactUsInfo(contact);
		LOGGER.info("After the add call and the contact " + contact != null
				? contact.getFirstname() : "No Distributor data" + " got added successfully");
		return jsend(response);
	}
	
}
