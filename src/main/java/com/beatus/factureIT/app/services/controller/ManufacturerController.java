package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.ManufacturerResponse;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.UserTypeIdAndProducts;
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
	
	public static JSendResponse<String> jsend(boolean response) {
		if (response) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<String> jsend(String response) {
		if (response != null) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<List<?>> jsend(List<?> response) {
		if (response == null || response == null) {
			return new JSendResponse<List<?>>(Constants.FAILURE, response);
		} else {
			return new JSendResponse<List<?>>(Constants.SUCCESS, response);
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
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_ALL_MANUFACTURERS_BY_AREA, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<ManufacturerResponse> getAllManufacturersInASpecificArea(HttpServletRequest request,
			ModelMap model, @RequestParam String latitude,  @RequestParam String longitude,  @RequestParam String radius) throws ClassNotFoundException, SQLException {
		List<Manufacturer> manufacturers = manufacturerService.getAllManufacturersInASpecificArea(latitude, longitude, radius);
		LOGGER.info("After the get call and the manufacturers are " + manufacturers != null
				? manufacturers.size() > 0 ? manufacturers.get(0).getFirstname() : "No Manufacturer data"
				: "No Manufacturer data");
		ManufacturerResponse resp = new ManufacturerResponse();
		resp.setManufacturers(manufacturers);
		return jsend(resp);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_ADD_MANUFACTURER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addManufacturerPost(HttpServletRequest request,
			ModelMap model, @RequestBody Manufacturer manufacturer) throws ClassNotFoundException, SQLException {
		String id = manufacturerService.addManufacturer(manufacturer);
		LOGGER.info("After the add call and the manufacturer " + manufacturer != null
				? manufacturer.getFirstname() : "No Manufacturer data" + " got added successfully");
		return jsend(id);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_EDIT_MANUFACTURER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> editManufacturerPost(HttpServletRequest request,
			ModelMap model, @RequestParam Manufacturer manufacturer) throws ClassNotFoundException, SQLException {
		boolean isManufacturerEdited = manufacturerService.editManufacturer(manufacturer);
		LOGGER.info("After the edit call and the manufacturer " + manufacturer != null
				? manufacturer.getFirstname() : "No Manufacturer data" + " got edited successfully");
		return jsend(isManufacturerEdited);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_ADD_PRODUCTS, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductsForManufacturerPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<Product> products, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = manufacturerService.addProductsForManufacturer(products, manufacturerId);
		LOGGER.info("After the add products call for the manufacturer with Id: " + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_ADD_CATEGORIES, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductCategoriesForManufacturerPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<ProductCategory> productCategories, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = manufacturerService.addProductCategoriesForManufacturer(productCategories, manufacturerId);
		LOGGER.info("After the add product categories call for the manufacturer with Id: " + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_PRODUCTS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductsOfManufacturerGet(HttpServletRequest request,
			ModelMap model, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		List<Product> manufacturerProducts = manufacturerService.getAllProductsOfManufacturer(manufacturerId);
		LOGGER.info("After the get call for the products of manufacturers with ID:" + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(manufacturerProducts);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_PRODUCT_CATEGORIES, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductCategoriesOfManufacturerGet(HttpServletRequest request,
			ModelMap model, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		List<ProductCategory> manufacturerProductCategories = manufacturerService.getAllProductCategoriesOfManufacturer(manufacturerId);
		LOGGER.info("After the get call for the product categories of manufacturers with ID:" + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(manufacturerProductCategories);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_ADD_RELATED_DISTRIBUTORS, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addManufacturerRelatedDistributorsPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<UserTypeIdAndProducts> distributorAndProducts, @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		boolean isDistributorAdded = manufacturerService.addManufacturerRelatedDistributors(distributorAndProducts, manufacturerId);
		LOGGER.info("After the add related distributors call for the manufacturer with Id: " + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(isDistributorAdded);
	}
	
	@RequestMapping(value = Constants.WEB_MANUFACTURER_GET_RELATED_DISTRIBUTORS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getManufacturerRelatedDistributorsGet(HttpServletRequest request,
			ModelMap model,  @RequestParam String manufacturerId) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = manufacturerService.getManufacturerRelatedDistributors(manufacturerId);
		LOGGER.info("After the get call for the distributors related to manufacturers with ID:" + manufacturerId != null
				? manufacturerId : "No Manufacturer data");
		return jsend(distributors);
	}
	
}
