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

import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.ManufacturerResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.service.ManufacturerService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_MANUFACTURER_REQUEST)
public class ManufacturerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerController.class);

	@Resource(name = "manufacturerService")
	private ManufacturerService manufacturerService;

	public static JSendResponse<ManufacturerResponse> jsend(ManufacturerResponse manufacturerResponse) {
		if (manufacturerResponse == null || manufacturerResponse.getManufacturers() == null
				|| manufacturerResponse.getManufacturers().get(0) == null) {
			return new JSendResponse<ManufacturerResponse>(Constants.FAILURE, manufacturerResponse);
		} else {
			return new JSendResponse<ManufacturerResponse>(Constants.SUCCESS, manufacturerResponse);
		}
	}
	
	public static JSendResponse<Manufacturer> jsend(Manufacturer manufacturer) {
		if (manufacturer == null || manufacturer == null) {
			return new JSendResponse<Manufacturer>(Constants.FAILURE, manufacturer);
		} else {
			return new JSendResponse<Manufacturer>(Constants.SUCCESS, manufacturer);
		}
	}

	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_MANUFACTURER_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Manufacturer> getManufacturerByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		Manufacturer manufacturer = manufacturerService.getManufacturerByManufacturerId(manufacturerId);
		LOGGER.info("After the get call and the manufacturer is " + manufacturer != null
				? manufacturer.getFirstname() : "No Manufacturer data");
		return jsend(manufacturer);
	}

	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_MANUFACTURER_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Manufacturer> getManufacturerByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Manufacturer manufacturer = manufacturerService.getManufacturerByUID(uid);
		LOGGER.info("After the get call and the manufacturer is " + manufacturer != null
				? manufacturer.getFirstname() : "No Manufacturer data");
		return jsend(manufacturer);
	}

	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_ALL_MANUFACTURERS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<ManufacturerResponse> getAllManufacturersGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
		LOGGER.info("After the get call and the manufacturers are " + manufacturers != null
				? manufacturers.size() > 0 ? manufacturers.get(0).getFirstname() : "No Manufacturer data"
				: "No Manufacturer data");
		ManufacturerResponse resp = new ManufacturerResponse();
		resp.setManufacturers(manufacturers);
		return jsend(resp);
	}
}
