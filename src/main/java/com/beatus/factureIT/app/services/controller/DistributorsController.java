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

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.DistributorResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.service.DistributorService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_DISTRIBUTOR_REQUEST)
public class DistributorsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorsController.class);

	@Resource(name = "distributorService")
	private DistributorService distributorService;

	public static JSendResponse<DistributorResponse> jsend(DistributorResponse distributorResponse) {
		if (distributorResponse == null || distributorResponse.getDistributors() == null
				|| distributorResponse.getDistributors().get(0) == null) {
			return new JSendResponse<DistributorResponse>(Constants.FAILURE, distributorResponse);
		} else {
			return new JSendResponse<DistributorResponse>(Constants.SUCCESS, distributorResponse);
		}
	}
	
	public static JSendResponse<Distributor> jsend(Distributor distributor) {
		if (distributor == null || distributor == null) {
			return new JSendResponse<Distributor>(Constants.FAILURE, distributor);
		} else {
			return new JSendResponse<Distributor>(Constants.SUCCESS, distributor);
		}
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Distributor> getDistributorByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		Distributor distributor = distributorService.getDistributorByDistributorId(distributorId);
		LOGGER.info("After the get call and the distributor is " + distributor != null
				? distributor.getFirstname() : "No Distributor data");
		return jsend(distributor);
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Distributor> getDistributorByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Distributor distributor = distributorService.getDistributorByUID(uid);
		LOGGER.info("After the get call and the distributor is " + distributor != null
				? distributor.getFirstname() : "No Distributor data");
		return jsend(distributor);
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_ALL_DISTRIBUTORS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<DistributorResponse> getAllDistributorsGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = distributorService.getAllDistributors();
		LOGGER.info("After the get call and the distributors are " + distributors != null
				? distributors.size() > 0 ? distributors.get(0).getFirstname() : "No Distributor data"
				: "No Distributor data");
		DistributorResponse resp = new DistributorResponse();
		resp.setDistributors(distributors);
		return jsend(resp);
	}
}
