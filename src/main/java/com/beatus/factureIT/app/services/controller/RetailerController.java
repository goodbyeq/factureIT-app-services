package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.RetailerResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.service.RetailerService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_RETAILER_REQUEST)
public class RetailerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerController.class);

	@Resource(name = "retailerService")
	private RetailerService retailerService;

	public static JSendResponse<RetailerResponse> jsend(RetailerResponse retailerResponse) {
		if (retailerResponse == null || retailerResponse.getRetailers() == null
				|| retailerResponse.getRetailers().get(0) == null) {
			return new JSendResponse<RetailerResponse>(Constants.FAILURE, retailerResponse);
		} else {
			return new JSendResponse<RetailerResponse>(Constants.SUCCESS, retailerResponse);
		}
	}
	
	public static JSendResponse<Retailer> jsend(Retailer retailer) {
		if (retailer == null || retailer == null) {
			return new JSendResponse<Retailer>(Constants.FAILURE, retailer);
		} else {
			return new JSendResponse<Retailer>(Constants.SUCCESS, retailer);
		}
	}

	@RequestMapping(value = Constants.WEB_RETAILER_GET_RETAILER_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Retailer> getRetailerByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		Retailer retailer = retailerService.getRetailerByRetailerId(retailerId);
		LOGGER.info("After the get call and the retailer is " + retailer != null
				? retailer.getFirstname() : "No Retailer data");
		return jsend(retailer);
	}

	@RequestMapping(value = Constants.WEB_RETAILER_GET_RETAILER_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Retailer> getRetailerByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Retailer retailer = retailerService.getRetailerByUID(uid);
		LOGGER.info("After the get call and the retailer is " + retailer != null
				? retailer.getFirstname() : "No Retailer data");
		return jsend(retailer);
	}

	@RequestMapping(value = Constants.WEB_RETAILER_GET_ALL_RETAILERS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<RetailerResponse> getAllRetailersGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Retailer> retailers = retailerService.getAllRetailers();
		LOGGER.info("After the get call and the retailers are " + retailers != null
				? retailers.size() > 0 ? retailers.get(0).getFirstname() : "No Retailer data"
				: "No Retailer data");
		RetailerResponse resp = new RetailerResponse();
		resp.setRetailers(retailers);
		return jsend(resp);
	}
}
